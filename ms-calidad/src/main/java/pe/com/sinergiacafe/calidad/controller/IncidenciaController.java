package pe.com.sinergiacafe.calidad.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.calidad.dto.IncidenciaRequestDTO;
import pe.com.sinergiacafe.calidad.dto.IncidenciaResponseDTO;
import pe.com.sinergiacafe.calidad.service.IncidenciaService;
import pe.com.sinergiacafe.calidad.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/incidencias")
@RequiredArgsConstructor
public class IncidenciaController {

    private final IncidenciaService incidenciaService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<IncidenciaResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(incidenciaService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<IncidenciaResponseDTO>> obtenerPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(incidenciaService.obtenerPorId(id)));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<ApiResponse<List<IncidenciaResponseDTO>>> listarPorEstado(
            @PathVariable String estado) {
        return ResponseEntity.ok(ApiResponse.ok(incidenciaService.listarPorEstado(estado)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<IncidenciaResponseDTO>> registrar(
            @Valid @RequestBody IncidenciaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Incidencia registrada exitosamente",
                        incidenciaService.registrar(request)));
    }

    @PatchMapping("/{id}/estado/{estado}")
    public ResponseEntity<ApiResponse<IncidenciaResponseDTO>> actualizarEstado(
            @PathVariable Long id,
            @PathVariable String estado) {
        return ResponseEntity.ok(ApiResponse.ok("Estado actualizado exitosamente",
                incidenciaService.actualizarEstado(id, estado)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        incidenciaService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Incidencia eliminada exitosamente", null));
    }
}