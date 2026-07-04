package pe.com.sinergiacafe.calidad.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.calidad.dto.EncuestaSatisfaccionRequestDTO;
import pe.com.sinergiacafe.calidad.dto.EncuestaSatisfaccionResponseDTO;
import pe.com.sinergiacafe.calidad.service.EncuestaSatisfaccionService;
import pe.com.sinergiacafe.calidad.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/encuestas-satisfaccion")
@RequiredArgsConstructor
public class EncuestaSatisfaccionController {

    private final EncuestaSatisfaccionService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<EncuestaSatisfaccionResponseDTO>>> listar() {
        return ResponseEntity.ok(ApiResponse.ok(service.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EncuestaSatisfaccionResponseDTO>> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(service.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EncuestaSatisfaccionResponseDTO>> crear(
            @Valid @RequestBody EncuestaSatisfaccionRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Encuesta creada correctamente", service.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EncuestaSatisfaccionResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody EncuestaSatisfaccionRequestDTO request) {

        return ResponseEntity.ok(ApiResponse.ok("Encuesta actualizada correctamente",
                service.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Encuesta eliminada correctamente", null));
    }
}