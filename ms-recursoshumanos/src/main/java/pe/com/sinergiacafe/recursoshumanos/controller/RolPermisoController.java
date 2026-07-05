package pe.com.sinergiacafe.recursoshumanos.controller;

import pe.com.sinergiacafe.recursoshumanos.dto.RolPermisoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.entity.RolPermisoEntity;
import pe.com.sinergiacafe.recursoshumanos.service.RolPermisoService;
import pe.com.sinergiacafe.recursoshumanos.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles-permisos")
@RequiredArgsConstructor
public class RolPermisoController {

    private final RolPermisoService service;

    @GetMapping
    public ApiResponse<List<RolPermisoEntity>> findAll() {
        return ApiResponse.ok(service.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<RolPermisoEntity> create(@Valid @RequestBody RolPermisoRequestDTO dto) {
        return ApiResponse.ok("Rol-Permiso asignado correctamente", service.create(dto));
    }

    @DeleteMapping("/{idRol}/{idPermiso}")
    public ApiResponse<Void> delete(@PathVariable Long idRol, @PathVariable Long idPermiso) {
        service.delete(idRol, idPermiso);
        return ApiResponse.ok("Rol-Permiso eliminado correctamente", null);
    }
}
