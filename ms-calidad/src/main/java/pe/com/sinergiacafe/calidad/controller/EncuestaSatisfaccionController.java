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

    private final EncuestaSatisfaccionService encuestaSatisfaccionService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<EncuestaSatisfaccionResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(encuestaSatisfaccionService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EncuestaSatisfaccionResponseDTO>> obtenerPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(encuestaSatisfaccionService.obtenerPorId(id)));
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<ApiResponse<List<EncuestaSatisfaccionResponseDTO>>> listarPorCliente(
            @PathVariable Long idCliente) {
        return ResponseEntity.ok(ApiResponse.ok(
                encuestaSatisfaccionService.listarPorCliente(idCliente)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EncuestaSatisfaccionResponseDTO>> registrar(
            @Valid @RequestBody EncuestaSatisfaccionRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Encuesta registrada exitosamente",
                        encuestaSatisfaccionService.registrar(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        encuestaSatisfaccionService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Encuesta eliminada exitosamente", null));
    }
}