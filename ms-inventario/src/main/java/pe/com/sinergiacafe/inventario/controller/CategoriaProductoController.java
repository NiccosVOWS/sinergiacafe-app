package pe.com.sinergiacafe.inventario.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.inventario.dto.CategoriaProductoRequestDTO;
import pe.com.sinergiacafe.inventario.dto.CategoriaProductoResponseDTO;
import pe.com.sinergiacafe.inventario.service.CategoriaProductoService;
import pe.com.sinergiacafe.inventario.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias-producto")
@RequiredArgsConstructor
public class CategoriaProductoController {

    private final CategoriaProductoService categoriaProductoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoriaProductoResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(categoriaProductoService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoriaProductoResponseDTO>> obtenerPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(categoriaProductoService.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CategoriaProductoResponseDTO>> crear(
            @Valid @RequestBody CategoriaProductoRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Categoría de producto creada exitosamente",
                        categoriaProductoService.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoriaProductoResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaProductoRequestDTO request) {
        return ResponseEntity.ok(ApiResponse.ok("Categoría de producto actualizada exitosamente",
                categoriaProductoService.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        categoriaProductoService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Categoría de producto eliminada exitosamente", null));
    }
}