package pe.com.sinergiacafe.mantenimiento.controller;

import pe.com.sinergiacafe.mantenimiento.dto.TipoEquipoRequestDTO;
import pe.com.sinergiacafe.mantenimiento.dto.TipoEquipoResponseDTO;
import pe.com.sinergiacafe.mantenimiento.service.TipoEquipoService;
import pe.com.sinergiacafe.mantenimiento.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tipos-equipo")
@RequiredArgsConstructor
public class TipoEquipoController {

    private final TipoEquipoService service;

    @GetMapping
    public ApiResponse<List<TipoEquipoResponseDTO>> findAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<TipoEquipoResponseDTO> findById(@PathVariable Long id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<TipoEquipoResponseDTO> create(@Valid @RequestBody TipoEquipoRequestDTO dto) {
        return ApiResponse.ok("TipoEquipo creado correctamente", service.create(dto));
    }

    @PutMapping("/{id}")
    public ApiResponse<TipoEquipoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody TipoEquipoRequestDTO dto) {
        return ApiResponse.ok("TipoEquipo actualizado correctamente", service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok("TipoEquipo eliminado correctamente", null);
    }
}
