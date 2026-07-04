package pe.com.sinergiacafe.recursoshumanos.controller;

import pe.com.sinergiacafe.recursoshumanos.dto.TurnoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.TurnoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.service.TurnoService;
import pe.com.sinergiacafe.recursoshumanos.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/turnos")
@RequiredArgsConstructor
public class TurnoController {

    private final TurnoService service;

    @GetMapping
    public ApiResponse<List<TurnoResponseDTO>> findAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<TurnoResponseDTO> findById(@PathVariable Long id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<TurnoResponseDTO> create(@Valid @RequestBody TurnoRequestDTO dto) {
        return ApiResponse.ok("Turno creado correctamente", service.create(dto));
    }

    @PutMapping("/{id}")
    public ApiResponse<TurnoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody TurnoRequestDTO dto) {
        return ApiResponse.ok("Turno actualizado correctamente", service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok("Turno eliminado correctamente", null);
    }
}
