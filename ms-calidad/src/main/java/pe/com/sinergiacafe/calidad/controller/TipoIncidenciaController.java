package pe.com.sinergiacafe.calidad.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.calidad.dto.TipoIncidenciaRequestDTO;
import pe.com.sinergiacafe.calidad.dto.TipoIncidenciaResponseDTO;
import pe.com.sinergiacafe.calidad.service.TipoIncidenciaService;
import pe.com.sinergiacafe.calidad.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tipos-incidencia")
@RequiredArgsConstructor
public class TipoIncidenciaController {

    private final TipoIncidenciaService tipoIncidenciaService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TipoIncidenciaResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(tipoIncidenciaService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TipoIncidenciaResponseDTO>> obtenerPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(tipoIncidenciaService.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TipoIncidenciaResponseDTO>> crear(
            @Valid @RequestBody TipoIncidenciaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Tipo de incidencia creado exitosamente",
                        tipoIncidenciaService.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TipoIncidenciaResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody TipoIncidenciaRequestDTO request) {
        return ResponseEntity.ok(ApiResponse.ok("Tipo de incidencia actualizado exitosamente",
                tipoIncidenciaService.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        tipoIncidenciaService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Tipo de incidencia eliminado exitosamente", null));
    }
}