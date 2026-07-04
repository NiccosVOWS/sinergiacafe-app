package pe.com.sinergiacafe.recursoshumanos.controller;

import pe.com.sinergiacafe.recursoshumanos.dto.AuditoriaRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.AuditoriaResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.service.AuditoriaService;
import pe.com.sinergiacafe.recursoshumanos.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auditoria")
@RequiredArgsConstructor
public class AuditoriaController {

    private final AuditoriaService service;

    @GetMapping
    public ApiResponse<List<AuditoriaResponseDTO>> findAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<AuditoriaResponseDTO> findById(@PathVariable Long id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<AuditoriaResponseDTO> create(@Valid @RequestBody AuditoriaRequestDTO dto) {
        return ApiResponse.ok("Auditoria creado correctamente", service.create(dto));
    }

    @PutMapping("/{id}")
    public ApiResponse<AuditoriaResponseDTO> update(@PathVariable Long id, @Valid @RequestBody AuditoriaRequestDTO dto) {
        return ApiResponse.ok("Auditoria actualizado correctamente", service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok("Auditoria eliminado correctamente", null);
    }
}
