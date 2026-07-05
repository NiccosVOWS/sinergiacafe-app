package pe.com.sinergiacafe.calidad.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.calidad.dto.EstandarCalidadRequestDTO;
import pe.com.sinergiacafe.calidad.dto.EstandarCalidadResponseDTO;
import pe.com.sinergiacafe.calidad.service.EstandarCalidadService;
import pe.com.sinergiacafe.calidad.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estandares-calidad")
@RequiredArgsConstructor
public class EstandarCalidadController {

    private final EstandarCalidadService estandarCalidadService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<EstandarCalidadResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(estandarCalidadService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EstandarCalidadResponseDTO>> obtenerPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(estandarCalidadService.obtenerPorId(id)));
    }

    @GetMapping("/aplicable/{aplicableA}")
    public ResponseEntity<ApiResponse<List<EstandarCalidadResponseDTO>>> listarPorAplicableA(
            @PathVariable String aplicableA) {
        return ResponseEntity.ok(ApiResponse.ok(
                estandarCalidadService.listarPorAplicableA(aplicableA)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EstandarCalidadResponseDTO>> crear(
            @Valid @RequestBody EstandarCalidadRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Estándar creado exitosamente",
                        estandarCalidadService.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EstandarCalidadResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody EstandarCalidadRequestDTO request) {
        return ResponseEntity.ok(ApiResponse.ok("Estándar actualizado exitosamente",
                estandarCalidadService.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        estandarCalidadService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Estándar eliminado exitosamente", null));
    }
}