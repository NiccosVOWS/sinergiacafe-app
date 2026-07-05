package pe.com.sinergiacafe.recursoshumanos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.recursoshumanos.dto.UsuarioRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.UsuarioResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.service.UsuarioService;
import pe.com.sinergiacafe.recursoshumanos.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UsuarioResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(usuarioService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(usuarioService.obtenerPorId(id)));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> obtenerPorUsername(
            @PathVariable String username) {
        return ResponseEntity.ok(ApiResponse.ok(usuarioService.obtenerPorUsername(username)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> crear(
            @Valid @RequestBody UsuarioRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Usuario creado exitosamente",
                        usuarioService.crear(request)));
    }

    @PatchMapping("/{id}/rol/{idRol}")
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> actualizarRol(
            @PathVariable Long id,
            @PathVariable Long idRol) {
        return ResponseEntity.ok(ApiResponse.ok("Rol actualizado exitosamente",
                usuarioService.actualizarRol(id, idRol)));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> actualizarEstado(
            @PathVariable Long id,
            @RequestParam Boolean activo) {
        return ResponseEntity.ok(ApiResponse.ok("Estado actualizado exitosamente",
                usuarioService.actualizarEstado(id, activo)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Usuario eliminado exitosamente", null));
    }
}