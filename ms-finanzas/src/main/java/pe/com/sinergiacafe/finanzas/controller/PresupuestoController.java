package pe.com.sinergiacafe.finanzas.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.finanzas.dto.PresupuestoRequestDTO;
import pe.com.sinergiacafe.finanzas.dto.PresupuestoResponseDTO;
import pe.com.sinergiacafe.finanzas.service.PresupuestoService;
import pe.com.sinergiacafe.finanzas.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/presupuestos")
@RequiredArgsConstructor
public class PresupuestoController {

    private final PresupuestoService presupuestoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PresupuestoResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(presupuestoService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PresupuestoResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(presupuestoService.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PresupuestoResponseDTO>> crear(
            @Valid @RequestBody PresupuestoRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok(
                        "Presupuesto creado exitosamente",
                        presupuestoService.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PresupuestoResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody PresupuestoRequestDTO request) {

        return ResponseEntity.ok(ApiResponse.ok(
                "Presupuesto actualizado exitosamente",
                presupuestoService.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {

        presupuestoService.eliminar(id);

        return ResponseEntity.ok(ApiResponse.ok(
                "Presupuesto eliminado exitosamente",
                null));
    }
}