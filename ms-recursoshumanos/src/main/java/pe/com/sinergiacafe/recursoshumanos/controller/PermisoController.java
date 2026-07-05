package pe.com.sinergiacafe.recursoshumanos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.recursoshumanos.dto.PermisoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.PermisoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.service.PermisoService;
import pe.com.sinergiacafe.recursoshumanos.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/permisos")
@RequiredArgsConstructor
public class PermisoController {

    private final PermisoService permisoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PermisoResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(permisoService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PermisoResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(permisoService.obtenerPorId(id)));
    }

    @GetMapping("/modulo/{modulo}")
    public ResponseEntity<ApiResponse<List<PermisoResponseDTO>>> listarPorModulo(
            @PathVariable String modulo) {
        return ResponseEntity.ok(ApiResponse.ok(permisoService.listarPorModulo(modulo)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PermisoResponseDTO>> crear(
            @Valid @RequestBody PermisoRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Permiso creado exitosamente",
                        permisoService.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PermisoResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody PermisoRequestDTO request) {
        return ResponseEntity.ok(ApiResponse.ok("Permiso actualizado exitosamente",
                permisoService.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        permisoService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Permiso eliminado exitosamente", null));
    }
}