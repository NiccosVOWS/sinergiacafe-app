package pe.com.sinergiacafe.pedidos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.pedidos.dto.EstadoPedidoResponseDTO;
import pe.com.sinergiacafe.pedidos.entity.EstadoPedidoEntity;
import pe.com.sinergiacafe.pedidos.repository.EstadoPedidoRepository;
import pe.com.sinergiacafe.pedidos.shared.ApiResponse;
import pe.com.sinergiacafe.pedidos.shared.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estados-pedido")
@RequiredArgsConstructor
public class EstadoPedidoController {

    private final EstadoPedidoRepository estadoPedidoRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<List<EstadoPedidoResponseDTO>>> listarTodos() {
        List<EstadoPedidoResponseDTO> lista = estadoPedidoRepository.findAll()
                .stream()
                .map(e -> EstadoPedidoResponseDTO.builder()
                        .idEstadoPedido(e.getIdEstadoPedido())
                        .nombre(e.getNombre())
                        .descripcion(e.getDescripcion())
                        .build())
                .toList();
        return ResponseEntity.ok(ApiResponse.ok(lista));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EstadoPedidoResponseDTO>> obtenerPorId(
            @PathVariable Long id) {
        EstadoPedidoEntity entity = estadoPedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EstadoPedido", id));
        return ResponseEntity.ok(ApiResponse.ok(EstadoPedidoResponseDTO.builder()
                .idEstadoPedido(entity.getIdEstadoPedido())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build()));
    }
}