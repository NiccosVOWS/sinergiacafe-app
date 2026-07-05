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
    public ResponseEntity<ApiResponse<ReporteFinancieroResponseDTO>> obtenerPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(reporteFinancieroService.obtenerPorId(id)));
    }

    @GetMapping("/periodo/{periodo}")
    public ResponseEntity<ApiResponse<ReporteFinancieroResponseDTO>> obtenerPorPeriodo(
            @PathVariable String periodo) {
        return ResponseEntity.ok(ApiResponse.ok(
                reporteFinancieroService.obtenerPorPeriodo(periodo)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ReporteFinancieroResponseDTO>> generar(
            @Valid @RequestBody ReporteFinancieroRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Reporte financiero generado exitosamente",
                        reporteFinancieroService.generar(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        reporteFinancieroService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok(
                "Reporte financiero eliminado exitosamente", null));
    }
}