package pe.com.sinergiacafe.inventario.controller;

import pe.com.sinergiacafe.inventario.dto.ProductoRequestDTO;
import pe.com.sinergiacafe.inventario.dto.ProductoResponseDTO;
import pe.com.sinergiacafe.inventario.service.ProductoService;
import pe.com.sinergiacafe.inventario.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService service;

    @GetMapping
    public ApiResponse<List<ProductoResponseDTO>> findAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductoResponseDTO> findById(@PathVariable Long id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ProductoResponseDTO> create(@Valid @RequestBody ProductoRequestDTO dto) {
        return ApiResponse.ok("Producto creado correctamente", service.create(dto));
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ProductoRequestDTO dto) {
        return ApiResponse.ok("Producto actualizado correctamente", service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok("Producto eliminado correctamente", null);
    }
}
