package pe.com.sinergiacafe.recursoshumanos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.recursoshumanos.dto.EmpleadoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.EmpleadoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.service.EmpleadoService;
import pe.com.sinergiacafe.recursoshumanos.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/empleados")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<EmpleadoResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(empleadoService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmpleadoResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(empleadoService.obtenerPorId(id)));
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<ApiResponse<EmpleadoResponseDTO>> obtenerPorDni(@PathVariable String dni) {
        return ResponseEntity.ok(ApiResponse.ok(empleadoService.obtenerPorDni(dni)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EmpleadoResponseDTO>> crear(
            @Valid @RequestBody EmpleadoRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Empleado creado exitosamente",
                        empleadoService.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmpleadoResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody EmpleadoRequestDTO request) {
        return ResponseEntity.ok(ApiResponse.ok("Empleado actualizado exitosamente",
                empleadoService.actualizar(id, request)));
    }

    @PatchMapping("/{id}/estado/{estadoLaboral}")
    public ResponseEntity<ApiResponse<EmpleadoResponseDTO>> actualizarEstadoLaboral(
            @PathVariable Long id,
            @PathVariable String estadoLaboral) {
        return ResponseEntity.ok(ApiResponse.ok("Estado laboral actualizado exitosamente",
                empleadoService.actualizarEstadoLaboral(id, estadoLaboral)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        empleadoService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Empleado eliminado exitosamente", null));
    }
}