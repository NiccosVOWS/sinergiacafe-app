package pe.com.sinergiacafe.calidad.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.calidad.dto.EstandarCalidadRequestDTO;
import pe.com.sinergiacafe.calidad.dto.EstandarCalidadResponseDTO;
import pe.com.sinergiacafe.calidad.service.EstandarCalidadService;
import pe.com.sinergiacafe.calidad.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estandares-calidad")
@RequiredArgsConstructor
public class EstandarCalidadController {

    private final EstandarCalidadService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<EstandarCalidadResponseDTO>>> listar() {
        return ResponseEntity.ok(ApiResponse.ok(service.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EstandarCalidadResponseDTO>> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(service.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EstandarCalidadResponseDTO>> crear(
            @Valid @RequestBody EstandarCalidadRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Estandar creado correctamente", service.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EstandarCalidadResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody EstandarCalidadRequestDTO request) {

        return ResponseEntity.ok(ApiResponse.ok("Estandar actualizado correctamente",
                service.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Estandar eliminado correctamente", null));
    }
}