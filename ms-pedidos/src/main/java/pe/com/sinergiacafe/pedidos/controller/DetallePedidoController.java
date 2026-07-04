package pe.com.sinergiacafe.pedidos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.pedidos.dto.DetallePedidoRequestDTO;
import pe.com.sinergiacafe.pedidos.dto.DetallePedidoResponseDTO;
import pe.com.sinergiacafe.pedidos.service.DetallePedidoService;
import pe.com.sinergiacafe.pedidos.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detalle-pedidos")
@RequiredArgsConstructor
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;

    @GetMapping("/pedido/{idPedido}")
    public ResponseEntity<ApiResponse<List<DetallePedidoResponseDTO>>> listarPorPedido(
            @PathVariable Long idPedido) {
        return ResponseEntity.ok(ApiResponse.ok(detallePedidoService.listarPorPedido(idPedido)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DetallePedidoResponseDTO>> agregar(
            @Valid @RequestBody DetallePedidoRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Detalle agregado exitosamente",
                        detallePedidoService.agregar(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        detallePedidoService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Detalle eliminado exitosamente", null));
    }
}