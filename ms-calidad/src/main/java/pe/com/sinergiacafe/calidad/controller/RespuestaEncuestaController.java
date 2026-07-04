package pe.com.sinergiacafe.calidad.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.calidad.dto.RespuestaEncuestaRequestDTO;
import pe.com.sinergiacafe.calidad.dto.RespuestaEncuestaResponseDTO;
import pe.com.sinergiacafe.calidad.service.RespuestaEncuestaService;
import pe.com.sinergiacafe.calidad.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/respuestas-encuesta")
@RequiredArgsConstructor
public class RespuestaEncuestaController {

    private final RespuestaEncuestaService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<RespuestaEncuestaResponseDTO>>> listar() {
        return ResponseEntity.ok(ApiResponse.ok(service.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RespuestaEncuestaResponseDTO>> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(service.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RespuestaEncuestaResponseDTO>> crear(
            @Valid @RequestBody RespuestaEncuestaRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Respuesta creada correctamente", service.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RespuestaEncuestaResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody RespuestaEncuestaRequestDTO request) {

        return ResponseEntity.ok(ApiResponse.ok("Respuesta actualizada correctamente",
                service.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Respuesta eliminada correctamente", null));
    }
}