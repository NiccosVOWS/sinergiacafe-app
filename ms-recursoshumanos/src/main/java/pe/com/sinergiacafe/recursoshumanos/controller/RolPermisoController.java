package pe.com.sinergiacafe.recursoshumanos.controller;

import pe.com.sinergiacafe.recursoshumanos.dto.RolPermisoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.RolPermisoResponseDTO;
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
    public ApiResponse<List<RolPermisoResponseDTO>> findAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<RolPermisoResponseDTO> findById(@PathVariable Long id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<RolPermisoResponseDTO> create(@Valid @RequestBody RolPermisoRequestDTO dto) {
        return ApiResponse.ok("RolPermiso creado correctamente", service.create(dto));
    }

    @PutMapping("/{id}")
    public ApiResponse<RolPermisoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody RolPermisoRequestDTO dto) {
        return ApiResponse.ok("RolPermiso actualizado correctamente", service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok("RolPermiso eliminado correctamente", null);
    }
}
