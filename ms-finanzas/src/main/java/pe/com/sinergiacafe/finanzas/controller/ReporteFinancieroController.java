package pe.com.sinergiacafe.finanzas.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.finanzas.dto.ReporteFinancieroRequestDTO;
import pe.com.sinergiacafe.finanzas.dto.ReporteFinancieroResponseDTO;
import pe.com.sinergiacafe.finanzas.service.ReporteFinancieroService;
import pe.com.sinergiacafe.finanzas.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reportes-financieros")
@RequiredArgsConstructor
public class ReporteFinancieroController {

    private final ReporteFinancieroService reporteFinancieroService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ReporteFinancieroResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(reporteFinancieroService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReporteFinancieroResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(reporteFinancieroService.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ReporteFinancieroResponseDTO>> crear(
            @Valid @RequestBody ReporteFinancieroRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok(
                        "Reporte financiero creado exitosamente",
                        reporteFinancieroService.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ReporteFinancieroResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ReporteFinancieroRequestDTO request) {

        return ResponseEntity.ok(ApiResponse.ok(
                "Reporte financiero actualizado exitosamente",
                reporteFinancieroService.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {

        reporteFinancieroService.eliminar(id);

        return ResponseEntity.ok(ApiResponse.ok(
                "Reporte financiero eliminado exitosamente",
                null));
    }
}