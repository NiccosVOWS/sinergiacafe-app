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

    private final InspeccionService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<InspeccionResponseDTO>>> listar() {
        return ResponseEntity.ok(ApiResponse.ok(service.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InspeccionResponseDTO>> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(service.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<InspeccionResponseDTO>> crear(
            @Valid @RequestBody InspeccionRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Inspección creada correctamente", service.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<InspeccionResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody InspeccionRequestDTO request) {

        return ResponseEntity.ok(ApiResponse.ok("Inspección actualizada correctamente",
                service.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Inspección eliminada correctamente", null));
    }
}