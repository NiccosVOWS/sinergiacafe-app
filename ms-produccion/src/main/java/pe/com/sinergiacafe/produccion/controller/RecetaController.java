package pe.com.sinergiacafe.produccion.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.produccion.dto.RecetaRequestDTO;
import pe.com.sinergiacafe.produccion.dto.RecetaResponseDTO;
import pe.com.sinergiacafe.produccion.service.RecetaService;
import pe.com.sinergiacafe.produccion.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recetas")
@RequiredArgsConstructor
public class RecetaController {

    private final RecetaService recetaService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<RecetaResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(recetaService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RecetaResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(recetaService.obtenerPorId(id)));
    }

    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<ApiResponse<RecetaResponseDTO>> obtenerPorProducto(
            @PathVariable Long idProducto) {
        return ResponseEntity.ok(ApiResponse.ok(recetaService.obtenerPorProducto(idProducto)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RecetaResponseDTO>> crear(
            @Valid @RequestBody RecetaRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Receta creada exitosamente", recetaService.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RecetaResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody RecetaRequestDTO request) {
        return ResponseEntity.ok(ApiResponse.ok("Receta actualizada exitosamente",
                recetaService.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        recetaService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Receta eliminada exitosamente", null));
    }
}