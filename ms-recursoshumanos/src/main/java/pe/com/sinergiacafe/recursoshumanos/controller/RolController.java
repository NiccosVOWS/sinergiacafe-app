package pe.com.sinergiacafe.recursoshumanos.controller;

import pe.com.sinergiacafe.recursoshumanos.dto.RolRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.RolResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.service.RolService;
import pe.com.sinergiacafe.recursoshumanos.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RolController {

    private final RolService service;

    @GetMapping
    public ApiResponse<List<RolResponseDTO>> findAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<RolResponseDTO> findById(@PathVariable Long id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<RolResponseDTO> create(@Valid @RequestBody RolRequestDTO dto) {
        return ApiResponse.ok("Rol creado correctamente", service.create(dto));
    }

    @PutMapping("/{id}")
    public ApiResponse<RolResponseDTO> update(@PathVariable Long id, @Valid @RequestBody RolRequestDTO dto) {
        return ApiResponse.ok("Rol actualizado correctamente", service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok("Rol eliminado correctamente", null);
    }
}
