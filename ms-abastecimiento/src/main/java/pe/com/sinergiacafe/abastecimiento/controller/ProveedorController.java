package pe.com.sinergiacafe.abastecimiento.controller;

import pe.com.sinergiacafe.abastecimiento.dto.ProveedorRequestDTO;
import pe.com.sinergiacafe.abastecimiento.dto.ProveedorResponseDTO;
import pe.com.sinergiacafe.abastecimiento.service.ProveedorService;
import pe.com.sinergiacafe.abastecimiento.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proveedores")
@RequiredArgsConstructor
public class ProveedorController {

    private final ProveedorService service;

    @GetMapping
    public ApiResponse<List<ProveedorResponseDTO>> findAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<ProveedorResponseDTO> findById(@PathVariable Long id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ProveedorResponseDTO> create(@Valid @RequestBody ProveedorRequestDTO dto) {
        return ApiResponse.ok("Proveedor creado correctamente", service.create(dto));
    }

    @PutMapping("/{id}")
    public ApiResponse<ProveedorResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ProveedorRequestDTO dto) {
        return ApiResponse.ok("Proveedor actualizado correctamente", service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok("Proveedor eliminado correctamente", null);
    }
}
