package pe.com.sinergiacafe.recursoshumanos.controller;

import pe.com.sinergiacafe.recursoshumanos.dto.UsuarioRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.UsuarioResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.service.UsuarioService;
import pe.com.sinergiacafe.recursoshumanos.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @GetMapping
    public ApiResponse<List<UsuarioResponseDTO>> findAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<UsuarioResponseDTO> findById(@PathVariable Long id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UsuarioResponseDTO> create(@Valid @RequestBody UsuarioRequestDTO dto) {
        return ApiResponse.ok("Usuario creado correctamente", service.create(dto));
    }

    @PutMapping("/{id}")
    public ApiResponse<UsuarioResponseDTO> update(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO dto) {
        return ApiResponse.ok("Usuario actualizado correctamente", service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok("Usuario eliminado correctamente", null);
    }
}
