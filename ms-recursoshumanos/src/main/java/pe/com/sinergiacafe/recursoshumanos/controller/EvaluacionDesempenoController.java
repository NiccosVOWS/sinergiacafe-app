package pe.com.sinergiacafe.recursoshumanos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.recursoshumanos.dto.EvaluacionDesempenoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.EvaluacionDesempenoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.service.EvaluacionDesempenoService;
import pe.com.sinergiacafe.recursoshumanos.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/evaluaciones-desempeno")
@RequiredArgsConstructor
public class EvaluacionDesempenoController {

    private final EvaluacionDesempenoService evaluacionDesempenoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<EvaluacionDesempenoResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(evaluacionDesempenoService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EvaluacionDesempenoResponseDTO>> obtenerPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(evaluacionDesempenoService.obtenerPorId(id)));
    }

    @GetMapping("/empleado/{idEmpleado}")
    public ResponseEntity<ApiResponse<List<EvaluacionDesempenoResponseDTO>>> listarPorEmpleado(
            @PathVariable Long idEmpleado) {
        return ResponseEntity.ok(ApiResponse.ok(
                evaluacionDesempenoService.listarPorEmpleado(idEmpleado)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EvaluacionDesempenoResponseDTO>> registrar(
            @Valid @RequestBody EvaluacionDesempenoRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Evaluación registrada exitosamente",
                        evaluacionDesempenoService.registrar(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        evaluacionDesempenoService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Evaluación eliminada exitosamente", null));
    }
}