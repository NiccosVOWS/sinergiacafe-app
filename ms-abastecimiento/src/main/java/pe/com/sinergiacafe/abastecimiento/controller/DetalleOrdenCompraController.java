package pe.com.sinergiacafe.abastecimiento.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.abastecimiento.dto.DetalleOrdenCompraRequestDTO;
import pe.com.sinergiacafe.abastecimiento.dto.DetalleOrdenCompraResponseDTO;
import pe.com.sinergiacafe.abastecimiento.service.DetalleOrdenCompraService;
import pe.com.sinergiacafe.abastecimiento.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detalle-ordenes-compra")
@RequiredArgsConstructor
public class DetalleOrdenCompraController {

    private final DetalleOrdenCompraService detalleOrdenCompraService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<DetalleOrdenCompraResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(detalleOrdenCompraService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DetalleOrdenCompraResponseDTO>> obtenerPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(detalleOrdenCompraService.obtenerPorId(id)));
    }

    @GetMapping("/orden/{idOrdenCompra}")
    public ResponseEntity<ApiResponse<List<DetalleOrdenCompraResponseDTO>>> listarPorOrden(
            @PathVariable Long idOrdenCompra) {
        return ResponseEntity.ok(ApiResponse.ok(
                detalleOrdenCompraService.listarPorOrden(idOrdenCompra)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DetalleOrdenCompraResponseDTO>> agregar(
            @Valid @RequestBody DetalleOrdenCompraRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Detalle agregado exitosamente",
                        detalleOrdenCompraService.agregar(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        detalleOrdenCompraService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Detalle eliminado exitosamente", null));
    }
}