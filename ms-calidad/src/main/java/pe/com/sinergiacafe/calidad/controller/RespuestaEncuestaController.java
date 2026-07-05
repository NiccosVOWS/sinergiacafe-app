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

    private final RespuestaEncuestaService respuestaEncuestaService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<RespuestaEncuestaResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(respuestaEncuestaService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RespuestaEncuestaResponseDTO>> obtenerPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(respuestaEncuestaService.obtenerPorId(id)));
    }

    @GetMapping("/encuesta/{idEncuestaSatisfaccion}")
    public ResponseEntity<ApiResponse<List<RespuestaEncuestaResponseDTO>>> listarPorEncuesta(
            @PathVariable Long idEncuestaSatisfaccion) {
        return ResponseEntity.ok(ApiResponse.ok(
                respuestaEncuestaService.listarPorEncuesta(idEncuestaSatisfaccion)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RespuestaEncuestaResponseDTO>> registrar(
            @Valid @RequestBody RespuestaEncuestaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Respuesta registrada exitosamente",
                        respuestaEncuestaService.registrar(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        respuestaEncuestaService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Respuesta eliminada exitosamente", null));
    }
}