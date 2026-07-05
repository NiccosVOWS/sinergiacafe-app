package pe.com.sinergiacafe.recursoshumanos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.recursoshumanos.dto.TurnoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.TurnoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.service.TurnoService;
import pe.com.sinergiacafe.recursoshumanos.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/turnos")
@RequiredArgsConstructor
public class TurnoController {

    private final TurnoService turnoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TurnoResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(turnoService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TurnoResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(turnoService.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TurnoResponseDTO>> crear(
            @Valid @RequestBody TurnoRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Turno creado exitosamente",
                        turnoService.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TurnoResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody TurnoRequestDTO request) {
        return ResponseEntity.ok(ApiResponse.ok("Turno actualizado exitosamente",
                turnoService.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        turnoService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Turno eliminado exitosamente", null));
    }
}