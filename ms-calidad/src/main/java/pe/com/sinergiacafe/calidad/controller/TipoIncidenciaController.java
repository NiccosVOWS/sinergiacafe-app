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

    private final TipoIncidenciaService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TipoIncidenciaResponseDTO>>> listar() {
        return ResponseEntity.ok(ApiResponse.ok(service.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TipoIncidenciaResponseDTO>> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(service.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TipoIncidenciaResponseDTO>> crear(
            @Valid @RequestBody TipoIncidenciaRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Tipo incidencia creado correctamente", service.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TipoIncidenciaResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody TipoIncidenciaRequestDTO request) {

        return ResponseEntity.ok(ApiResponse.ok("Tipo incidencia actualizado correctamente",
                service.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Tipo incidencia eliminado correctamente", null));
    }
}