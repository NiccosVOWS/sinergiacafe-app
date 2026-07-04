package pe.com.sinergiacafe.inventario.controller;

import pe.com.sinergiacafe.inventario.dto.CategoriaProductoRequestDTO;
import pe.com.sinergiacafe.inventario.dto.CategoriaProductoResponseDTO;
import pe.com.sinergiacafe.inventario.service.CategoriaProductoService;
import pe.com.sinergiacafe.inventario.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias-producto")
@RequiredArgsConstructor
public class CategoriaProductoController {

    private final CategoriaProductoService service;

    @GetMapping
    public ApiResponse<List<CategoriaProductoResponseDTO>> findAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<CategoriaProductoResponseDTO> findById(@PathVariable Long id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CategoriaProductoResponseDTO> create(@Valid @RequestBody CategoriaProductoRequestDTO dto) {
        return ApiResponse.ok("CategoriaProducto creado correctamente", service.create(dto));
    }

    @PutMapping("/{id}")
    public ApiResponse<CategoriaProductoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody CategoriaProductoRequestDTO dto) {
        return ApiResponse.ok("CategoriaProducto actualizado correctamente", service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok("CategoriaProducto eliminado correctamente", null);
    }
}
