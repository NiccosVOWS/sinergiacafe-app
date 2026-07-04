package pe.com.sinergiacafe.recursoshumanos.controller;

import pe.com.sinergiacafe.recursoshumanos.dto.PermisoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.PermisoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.service.PermisoService;
import pe.com.sinergiacafe.recursoshumanos.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/permisos")
@RequiredArgsConstructor
public class PermisoController {

    private final PermisoService service;

    @GetMapping
    public ApiResponse<List<PermisoResponseDTO>> findAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<PermisoResponseDTO> findById(@PathVariable Long id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<PermisoResponseDTO> create(@Valid @RequestBody PermisoRequestDTO dto) {
        return ApiResponse.ok("Permiso creado correctamente", service.create(dto));
    }

    @PutMapping("/{id}")
    public ApiResponse<PermisoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody PermisoRequestDTO dto) {
        return ApiResponse.ok("Permiso actualizado correctamente", service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok("Permiso eliminado correctamente", null);
    }
}
