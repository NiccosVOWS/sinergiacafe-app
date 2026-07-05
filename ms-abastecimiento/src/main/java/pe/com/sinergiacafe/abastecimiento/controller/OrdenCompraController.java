package pe.com.sinergiacafe.abastecimiento.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.abastecimiento.dto.OrdenCompraRequestDTO;
import pe.com.sinergiacafe.abastecimiento.dto.OrdenCompraResponseDTO;
import pe.com.sinergiacafe.abastecimiento.service.OrdenCompraService;
import pe.com.sinergiacafe.abastecimiento.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ordenes-compra")
@RequiredArgsConstructor
public class OrdenCompraController {

    private final OrdenCompraService ordenCompraService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrdenCompraResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(ordenCompraService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrdenCompraResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(ordenCompraService.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<OrdenCompraResponseDTO>> crear(
            @Valid @RequestBody OrdenCompraRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Orden de compra creada exitosamente",
                        ordenCompraService.crear(request)));
    }

    @PatchMapping("/{id}/estado/{estado}")
    public ResponseEntity<ApiResponse<OrdenCompraResponseDTO>> actualizarEstado(
            @PathVariable Long id,
            @PathVariable String estado) {
        return ResponseEntity.ok(ApiResponse.ok("Estado actualizado exitosamente",
                ordenCompraService.actualizarEstado(id, estado)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        ordenCompraService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Orden de compra eliminada exitosamente", null));
    }
}