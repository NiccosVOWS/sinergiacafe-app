package pe.com.sinergiacafe.inventario.controller;

import pe.com.sinergiacafe.inventario.dto.InsumoRequestDTO;
import pe.com.sinergiacafe.inventario.dto.InsumoResponseDTO;
import pe.com.sinergiacafe.inventario.service.InsumoService;
import pe.com.sinergiacafe.inventario.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/insumos")
@RequiredArgsConstructor
public class InsumoController {

    private final InsumoService service;

    @GetMapping
    public ApiResponse<List<InsumoResponseDTO>> findAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<InsumoResponseDTO> findById(@PathVariable Long id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<InsumoResponseDTO> create(@Valid @RequestBody InsumoRequestDTO dto) {
        return ApiResponse.ok("Insumo creado correctamente", service.create(dto));
    }

    @PutMapping("/{id}")
    public ApiResponse<InsumoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody InsumoRequestDTO dto) {
        return ApiResponse.ok("Insumo actualizado correctamente", service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok("Insumo eliminado correctamente", null);
    }
}
