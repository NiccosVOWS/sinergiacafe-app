package pe.com.sinergiacafe.recursoshumanos.controller;

import pe.com.sinergiacafe.recursoshumanos.dto.EmpleadoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.EmpleadoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.service.EmpleadoService;
import pe.com.sinergiacafe.recursoshumanos.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/empleados")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoService service;

    @GetMapping
    public ApiResponse<List<EmpleadoResponseDTO>> findAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<EmpleadoResponseDTO> findById(@PathVariable Long id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<EmpleadoResponseDTO> create(@Valid @RequestBody EmpleadoRequestDTO dto) {
        return ApiResponse.ok("Empleado creado correctamente", service.create(dto));
    }

    @PutMapping("/{id}")
    public ApiResponse<EmpleadoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody EmpleadoRequestDTO dto) {
        return ApiResponse.ok("Empleado actualizado correctamente", service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok("Empleado eliminado correctamente", null);
    }
}
