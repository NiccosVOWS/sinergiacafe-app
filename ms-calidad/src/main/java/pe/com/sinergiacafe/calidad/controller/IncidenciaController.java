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

    private final IncidenciaService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<IncidenciaResponseDTO>>> listar() {
        return ResponseEntity.ok(ApiResponse.ok(service.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<IncidenciaResponseDTO>> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(service.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<IncidenciaResponseDTO>> crear(
            @Valid @RequestBody IncidenciaRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Incidencia creada correctamente", service.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<IncidenciaResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody IncidenciaRequestDTO request) {

        return ResponseEntity.ok(ApiResponse.ok("Incidencia actualizada correctamente",
                service.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Incidencia eliminada correctamente", null));
    }
}