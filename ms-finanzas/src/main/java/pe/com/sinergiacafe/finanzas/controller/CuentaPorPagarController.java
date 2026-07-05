package pe.com.sinergiacafe.finanzas.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.finanzas.dto.CuentaPorPagarRequestDTO;
import pe.com.sinergiacafe.finanzas.dto.CuentaPorPagarResponseDTO;
import pe.com.sinergiacafe.finanzas.service.CuentaPorPagarService;
import pe.com.sinergiacafe.finanzas.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cuentas-por-pagar")
@RequiredArgsConstructor
public class CuentaPorPagarController {

    private final CuentaPorPagarService cuentaPorPagarService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CuentaPorPagarResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(cuentaPorPagarService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CuentaPorPagarResponseDTO>> obtenerPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(cuentaPorPagarService.obtenerPorId(id)));
    }

    @GetMapping("/proveedor/{idProveedor}")
    public ResponseEntity<ApiResponse<List<CuentaPorPagarResponseDTO>>> listarPorProveedor(
            @PathVariable Long idProveedor) {
        return ResponseEntity.ok(ApiResponse.ok(
                cuentaPorPagarService.listarPorProveedor(idProveedor)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CuentaPorPagarResponseDTO>> crear(
            @Valid @RequestBody CuentaPorPagarRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Cuenta por pagar creada exitosamente",
                        cuentaPorPagarService.crear(request)));
    }

    @PatchMapping("/{id}/estado/{estado}")
    public ResponseEntity<ApiResponse<CuentaPorPagarResponseDTO>> actualizarEstado(
            @PathVariable Long id,
            @PathVariable String estado) {
        return ResponseEntity.ok(ApiResponse.ok("Estado actualizado exitosamente",
                cuentaPorPagarService.actualizarEstado(id, estado)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        cuentaPorPagarService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok(
                "Cuenta por pagar eliminada exitosamente", null));
    }
}