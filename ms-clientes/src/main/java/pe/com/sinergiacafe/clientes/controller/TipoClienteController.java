package pe.com.sinergiacafe.clientes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.clientes.dto.TipoClienteResponseDTO;
import pe.com.sinergiacafe.clientes.entity.TipoClienteEntity;
import pe.com.sinergiacafe.clientes.repository.TipoClienteRepository;
import pe.com.sinergiacafe.clientes.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tipos-cliente")
@RequiredArgsConstructor
public class TipoClienteController {

    private final TipoClienteRepository tipoClienteRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TipoClienteResponseDTO>>> listarTodos() {
        List<TipoClienteResponseDTO> lista = tipoClienteRepository.findAll()
                .stream()
                .map(e -> TipoClienteResponseDTO.builder()
                        .idTipoCliente(e.getIdTipoCliente())
                        .nombre(e.getNombre())
                        .descripcion(e.getDescripcion())
                        .descuentoPct(e.getDescuentoPct())
                        .activo(e.getActivo())
                        .build())
                .toList();
        return ResponseEntity.ok(ApiResponse.ok(lista));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TipoClienteResponseDTO>> obtenerPorId(@PathVariable Long id) {
        TipoClienteEntity entity = tipoClienteRepository.findById(id)
                .orElseThrow(() -> new pe.com.sinergiacafe.clientes.shared.ResourceNotFoundException("TipoCliente", id));
        return ResponseEntity.ok(ApiResponse.ok(TipoClienteResponseDTO.builder()
                .idTipoCliente(entity.getIdTipoCliente())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .descuentoPct(entity.getDescuentoPct())
                .activo(entity.getActivo())
                .build()));
    }
}