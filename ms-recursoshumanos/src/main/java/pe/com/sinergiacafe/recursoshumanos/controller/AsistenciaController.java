package pe.com.sinergiacafe.recursoshumanos.controller;

import pe.com.sinergiacafe.recursoshumanos.dto.AsistenciaRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.AsistenciaResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.service.AsistenciaService;
import pe.com.sinergiacafe.recursoshumanos.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/asistencias")
@RequiredArgsConstructor
public class AsistenciaController {

    private final AsistenciaService service;

    @GetMapping
    public ApiResponse<List<AsistenciaResponseDTO>> findAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<AsistenciaResponseDTO> findById(@PathVariable Long id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<AsistenciaResponseDTO> create(@Valid @RequestBody AsistenciaRequestDTO dto) {
        return ApiResponse.ok("Asistencia creado correctamente", service.create(dto));
    }

    @PutMapping("/{id}")
    public ApiResponse<AsistenciaResponseDTO> update(@PathVariable Long id, @Valid @RequestBody AsistenciaRequestDTO dto) {
        return ApiResponse.ok("Asistencia actualizado correctamente", service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok("Asistencia eliminado correctamente", null);
    }
}
