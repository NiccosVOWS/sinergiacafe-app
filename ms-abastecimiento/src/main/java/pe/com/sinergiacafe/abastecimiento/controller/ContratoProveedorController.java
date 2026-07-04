package pe.com.sinergiacafe.abastecimiento.controller;

import pe.com.sinergiacafe.abastecimiento.dto.ContratoProveedorRequestDTO;
import pe.com.sinergiacafe.abastecimiento.dto.ContratoProveedorResponseDTO;
import pe.com.sinergiacafe.abastecimiento.service.ContratoProveedorService;
import pe.com.sinergiacafe.abastecimiento.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contratos-proveedor")
@RequiredArgsConstructor
public class ContratoProveedorController {

    private final ContratoProveedorService service;

    @GetMapping
    public ApiResponse<List<ContratoProveedorResponseDTO>> findAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<ContratoProveedorResponseDTO> findById(@PathVariable Long id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ContratoProveedorResponseDTO> create(@Valid @RequestBody ContratoProveedorRequestDTO dto) {
        return ApiResponse.ok("ContratoProveedor creado correctamente", service.create(dto));
    }

    @PutMapping("/{id}")
    public ApiResponse<ContratoProveedorResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ContratoProveedorRequestDTO dto) {
        return ApiResponse.ok("ContratoProveedor actualizado correctamente", service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok("ContratoProveedor eliminado correctamente", null);
    }
}
