package pe.com.sinergiacafe.calidad.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.calidad.dto.ResultadoInspeccionRequestDTO;
import pe.com.sinergiacafe.calidad.dto.ResultadoInspeccionResponseDTO;
import pe.com.sinergiacafe.calidad.service.ResultadoInspeccionService;
import pe.com.sinergiacafe.calidad.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resultados-inspeccion")
@RequiredArgsConstructor
public class ResultadoInspeccionController {

    private final ResultadoInspeccionService resultadoInspeccionService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ResultadoInspeccionResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(resultadoInspeccionService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ResultadoInspeccionResponseDTO>> obtenerPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(resultadoInspeccionService.obtenerPorId(id)));
    }

    @GetMapping("/inspeccion/{idInspeccion}")
    public ResponseEntity<ApiResponse<List<ResultadoInspeccionResponseDTO>>> listarPorInspeccion(
            @PathVariable Long idInspeccion) {
        return ResponseEntity.ok(ApiResponse.ok(
                resultadoInspeccionService.listarPorInspeccion(idInspeccion)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ResultadoInspeccionResponseDTO>> registrar(
            @Valid @RequestBody ResultadoInspeccionRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Resultado de inspección registrado exitosamente",
                        resultadoInspeccionService.registrar(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        resultadoInspeccionService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Resultado eliminado exitosamente", null));
    }
}