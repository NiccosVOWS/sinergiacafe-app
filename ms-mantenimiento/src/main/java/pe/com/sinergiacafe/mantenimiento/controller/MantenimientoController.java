package pe.com.sinergiacafe.mantenimiento.controller;

import pe.com.sinergiacafe.mantenimiento.dto.MantenimientoRequestDTO;
import pe.com.sinergiacafe.mantenimiento.dto.MantenimientoResponseDTO;
import pe.com.sinergiacafe.mantenimiento.service.MantenimientoService;
import pe.com.sinergiacafe.mantenimiento.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mantenimientos")
@RequiredArgsConstructor
public class MantenimientoController {

    private final MantenimientoService service;

    @GetMapping
    public ApiResponse<List<MantenimientoResponseDTO>> findAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<MantenimientoResponseDTO> findById(@PathVariable Long id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<MantenimientoResponseDTO> create(@Valid @RequestBody MantenimientoRequestDTO dto) {
        return ApiResponse.ok("Mantenimiento creado correctamente", service.create(dto));
    }

    @PutMapping("/{id}")
    public ApiResponse<MantenimientoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody MantenimientoRequestDTO dto) {
        return ApiResponse.ok("Mantenimiento actualizado correctamente", service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok("Mantenimiento eliminado correctamente", null);
    }
}
