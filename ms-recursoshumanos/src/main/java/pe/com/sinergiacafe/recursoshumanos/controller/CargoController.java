package pe.com.sinergiacafe.recursoshumanos.controller;

import pe.com.sinergiacafe.recursoshumanos.dto.CargoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.CargoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.service.CargoService;
import pe.com.sinergiacafe.recursoshumanos.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cargos")
@RequiredArgsConstructor
public class CargoController {

    private final CargoService service;

    @GetMapping
    public ApiResponse<List<CargoResponseDTO>> findAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<CargoResponseDTO> findById(@PathVariable Long id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CargoResponseDTO> create(@Valid @RequestBody CargoRequestDTO dto) {
        return ApiResponse.ok("Cargo creado correctamente", service.create(dto));
    }

    @PutMapping("/{id}")
    public ApiResponse<CargoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody CargoRequestDTO dto) {
        return ApiResponse.ok("Cargo actualizado correctamente", service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok("Cargo eliminado correctamente", null);
    }
}
