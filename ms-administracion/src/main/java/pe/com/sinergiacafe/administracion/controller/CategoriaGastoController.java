package pe.com.sinergiacafe.administracion.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.administracion.dto.CategoriaGastoRequestDTO;
import pe.com.sinergiacafe.administracion.dto.CategoriaGastoResponseDTO;
import pe.com.sinergiacafe.administracion.service.CategoriaGastoService;
import pe.com.sinergiacafe.administracion.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias-gasto")
@RequiredArgsConstructor
public class CategoriaGastoController {

    private final CategoriaGastoService categoriaGastoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoriaGastoResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(categoriaGastoService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoriaGastoResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(categoriaGastoService.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CategoriaGastoResponseDTO>> crear(
            @Valid @RequestBody CategoriaGastoRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Categoría de gasto creada exitosamente",
                        categoriaGastoService.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoriaGastoResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaGastoRequestDTO request) {

        return ResponseEntity.ok(ApiResponse.ok(
                "Categoría de gasto actualizada exitosamente",
                categoriaGastoService.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {

        categoriaGastoService.eliminar(id);

        return ResponseEntity.ok(ApiResponse.ok(
                "Categoría de gasto eliminada exitosamente",
                null));
    }
}