package pe.com.sinergiacafe.mantenimiento.controller;

import pe.com.sinergiacafe.mantenimiento.dto.TipoMantenimientoRequestDTO;
import pe.com.sinergiacafe.mantenimiento.dto.TipoMantenimientoResponseDTO;
import pe.com.sinergiacafe.mantenimiento.service.TipoMantenimientoService;
import pe.com.sinergiacafe.mantenimiento.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tipos-mantenimiento")
@RequiredArgsConstructor
public class TipoMantenimientoController {

    private final TipoMantenimientoService service;

    @GetMapping
    public ApiResponse<List<TipoMantenimientoResponseDTO>> findAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<TipoMantenimientoResponseDTO> findById(@PathVariable Long id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<TipoMantenimientoResponseDTO> create(@Valid @RequestBody TipoMantenimientoRequestDTO dto) {
        return ApiResponse.ok("TipoMantenimiento creado correctamente", service.create(dto));
    }

    @PutMapping("/{id}")
    public ApiResponse<TipoMantenimientoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody TipoMantenimientoRequestDTO dto) {
        return ApiResponse.ok("TipoMantenimiento actualizado correctamente", service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok("TipoMantenimiento eliminado correctamente", null);
    }
}
