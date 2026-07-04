package pe.com.sinergiacafe.mantenimiento.controller;

import pe.com.sinergiacafe.mantenimiento.dto.EquipoRequestDTO;
import pe.com.sinergiacafe.mantenimiento.dto.EquipoResponseDTO;
import pe.com.sinergiacafe.mantenimiento.service.EquipoService;
import pe.com.sinergiacafe.mantenimiento.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/equipos")
@RequiredArgsConstructor
public class EquipoController {

    private final EquipoService service;

    @GetMapping
    public ApiResponse<List<EquipoResponseDTO>> findAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<EquipoResponseDTO> findById(@PathVariable Long id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<EquipoResponseDTO> create(@Valid @RequestBody EquipoRequestDTO dto) {
        return ApiResponse.ok("Equipo creado correctamente", service.create(dto));
    }

    @PutMapping("/{id}")
    public ApiResponse<EquipoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody EquipoRequestDTO dto) {
        return ApiResponse.ok("Equipo actualizado correctamente", service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok("Equipo eliminado correctamente", null);
    }
}
