package pe.com.sinergiacafe.recursoshumanos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.recursoshumanos.dto.AsistenciaRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.AsistenciaResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.service.AsistenciaService;
import pe.com.sinergiacafe.recursoshumanos.shared.ApiResponse;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/asistencias")
@RequiredArgsConstructor
public class AsistenciaController {

    private final AsistenciaService asistenciaService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<AsistenciaResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(asistenciaService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AsistenciaResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(asistenciaService.obtenerPorId(id)));
    }

    @GetMapping("/empleado/{idEmpleado}")
    public ResponseEntity<ApiResponse<List<AsistenciaResponseDTO>>> listarPorEmpleado(
            @PathVariable Long idEmpleado) {
        return ResponseEntity.ok(ApiResponse.ok(asistenciaService.listarPorEmpleado(idEmpleado)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AsistenciaResponseDTO>> registrar(
            @Valid @RequestBody AsistenciaRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Asistencia registrada exitosamente",
                        asistenciaService.registrar(request)));
    }

    @PatchMapping("/{id}/hora-salida")
    public ResponseEntity<ApiResponse<AsistenciaResponseDTO>> actualizarHoraSalida(
            @PathVariable Long id,
            @RequestParam LocalTime horaSalida) {
        return ResponseEntity.ok(ApiResponse.ok("Hora de salida registrada exitosamente",
                asistenciaService.actualizarHoraSalida(id, horaSalida)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        asistenciaService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Asistencia eliminada exitosamente", null));
    }
}