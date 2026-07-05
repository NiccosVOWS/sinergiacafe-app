package pe.com.sinergiacafe.inventario.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.inventario.dto.ProductoRequestDTO;
import pe.com.sinergiacafe.inventario.dto.ProductoResponseDTO;
import pe.com.sinergiacafe.inventario.service.ProductoService;
import pe.com.sinergiacafe.inventario.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductoResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(productoService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductoResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(productoService.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductoResponseDTO>> crear(
            @Valid @RequestBody ProductoRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Producto creado exitosamente",
                        productoService.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductoResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProductoRequestDTO request) {
        return ResponseEntity.ok(ApiResponse.ok("Producto actualizado exitosamente",
                productoService.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Producto eliminado exitosamente", null));
    }
}