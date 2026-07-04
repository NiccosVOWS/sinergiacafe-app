package pe.com.sinergiacafe.pedidos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.pedidos.dto.PedidoRequestDTO;
import pe.com.sinergiacafe.pedidos.dto.PedidoResponseDTO;
import pe.com.sinergiacafe.pedidos.service.PedidoService;
import pe.com.sinergiacafe.pedidos.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PedidoResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(pedidoService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PedidoResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(pedidoService.obtenerPorId(id)));
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<ApiResponse<List<PedidoResponseDTO>>> listarPorCliente(
            @PathVariable Long idCliente) {
        return ResponseEntity.ok(ApiResponse.ok(pedidoService.listarPorCliente(idCliente)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PedidoResponseDTO>> crear(
            @Valid @RequestBody PedidoRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Pedido creado exitosamente", pedidoService.crear(request)));
    }

    @PatchMapping("/{id}/estado/{idEstadoPedido}")
    public ResponseEntity<ApiResponse<PedidoResponseDTO>> actualizarEstado(
            @PathVariable Long id,
            @PathVariable Long idEstadoPedido) {
        return ResponseEntity.ok(ApiResponse.ok("Estado actualizado exitosamente",
                pedidoService.actualizarEstado(id, idEstadoPedido)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        pedidoService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Pedido eliminado exitosamente", null));
    }
}