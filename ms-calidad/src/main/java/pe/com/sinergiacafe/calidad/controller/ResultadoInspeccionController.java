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

    private final ResultadoInspeccionService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ResultadoInspeccionResponseDTO>>> listar() {
        return ResponseEntity.ok(ApiResponse.ok(service.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ResultadoInspeccionResponseDTO>> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(service.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ResultadoInspeccionResponseDTO>> crear(
            @Valid @RequestBody ResultadoInspeccionRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Resultado de inspección creado correctamente", service.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ResultadoInspeccionResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ResultadoInspeccionRequestDTO request) {

        return ResponseEntity.ok(ApiResponse.ok("Resultado actualizado correctamente",
                service.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Resultado eliminado correctamente", null));
    }
}