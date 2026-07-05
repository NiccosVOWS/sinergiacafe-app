package pe.com.sinergiacafe.calidad.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.calidad.dto.InspeccionRequestDTO;
import pe.com.sinergiacafe.calidad.dto.InspeccionResponseDTO;
import pe.com.sinergiacafe.calidad.service.InspeccionService;
import pe.com.sinergiacafe.calidad.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inspecciones")
@RequiredArgsConstructor
public class InspeccionController {

    private final InspeccionService inspeccionService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<InspeccionResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(inspeccionService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InspeccionResponseDTO>> obtenerPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(inspeccionService.obtenerPorId(id)));
    }

    @GetMapping("/area/{area}")
    public ResponseEntity<ApiResponse<List<InspeccionResponseDTO>>> listarPorArea(
            @PathVariable String area) {
        return ResponseEntity.ok(ApiResponse.ok(inspeccionService.listarPorArea(area)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<InspeccionResponseDTO>> registrar(
            @Valid @RequestBody InspeccionRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Inspección registrada exitosamente",
                        inspeccionService.registrar(request)));
    }

    @PatchMapping("/{id}/resultado/{resultadoGeneral}")
    public ResponseEntity<ApiResponse<InspeccionResponseDTO>> actualizarResultado(
            @PathVariable Long id,
            @PathVariable String resultadoGeneral) {
        return ResponseEntity.ok(ApiResponse.ok("Resultado actualizado exitosamente",
                inspeccionService.actualizarResultado(id, resultadoGeneral)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        inspeccionService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Inspección eliminada exitosamente", null));
    }
}