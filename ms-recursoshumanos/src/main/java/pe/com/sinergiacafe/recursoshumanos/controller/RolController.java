package pe.com.sinergiacafe.recursoshumanos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.recursoshumanos.dto.RolRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.RolResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.service.RolService;
import pe.com.sinergiacafe.recursoshumanos.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RolController {

    private final RolService rolService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<RolResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(rolService.listarTodos()));
    }

    @GetMapping("/activos")
    public ResponseEntity<ApiResponse<List<RolResponseDTO>>> listarActivos() {
        return ResponseEntity.ok(ApiResponse.ok(rolService.listarActivos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RolResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(rolService.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RolResponseDTO>> crear(
            @Valid @RequestBody RolRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Rol creado exitosamente",
                        rolService.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RolResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody RolRequestDTO request) {
        return ResponseEntity.ok(ApiResponse.ok("Rol actualizado exitosamente",
                rolService.actualizar(id, request)));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<ApiResponse<RolResponseDTO>> actualizarEstado(
            @PathVariable Long id,
            @RequestParam Boolean activo) {
        return ResponseEntity.ok(ApiResponse.ok("Estado del rol actualizado exitosamente",
                rolService.actualizarEstado(id, activo)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        rolService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Rol eliminado exitosamente", null));
    }
}