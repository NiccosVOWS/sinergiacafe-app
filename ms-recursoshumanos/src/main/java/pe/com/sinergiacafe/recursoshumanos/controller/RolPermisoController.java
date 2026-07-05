package pe.com.sinergiacafe.recursoshumanos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.recursoshumanos.dto.RolPermisoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.RolPermisoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.service.RolPermisoService;
import pe.com.sinergiacafe.recursoshumanos.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rol-permisos")
@RequiredArgsConstructor
public class RolPermisoController {

    private final RolPermisoService rolPermisoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<RolPermisoResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(rolPermisoService.listarTodos()));
    }

    @GetMapping("/rol/{idRol}")
    public ResponseEntity<ApiResponse<List<RolPermisoResponseDTO>>> listarPorRol(
            @PathVariable Long idRol) {
        return ResponseEntity.ok(ApiResponse.ok(rolPermisoService.listarPorRol(idRol)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RolPermisoResponseDTO>> asignar(
            @Valid @RequestBody RolPermisoRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Permiso asignado al rol exitosamente",
                        rolPermisoService.asignar(request)));
    }

    @DeleteMapping("/{idRol}/{idPermiso}")
    public ResponseEntity<ApiResponse<Void>> eliminar(
            @PathVariable Long idRol,
            @PathVariable Long idPermiso) {
        rolPermisoService.eliminar(idRol, idPermiso);
        return ResponseEntity.ok(ApiResponse.ok("Permiso removido del rol exitosamente", null));
    }
}