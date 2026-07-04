package pe.com.sinergiacafe.produccion.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.produccion.dto.InsumoRequestDTO;
import pe.com.sinergiacafe.produccion.dto.InsumoResponseDTO;
import pe.com.sinergiacafe.produccion.service.InsumoService;
import pe.com.sinergiacafe.produccion.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/insumos")
@RequiredArgsConstructor
public class InsumoController {

    private final InsumoService insumoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<InsumoResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(insumoService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InsumoResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(insumoService.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<InsumoResponseDTO>> crear(
            @Valid @RequestBody InsumoRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Insumo creado exitosamente", insumoService.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<InsumoResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody InsumoRequestDTO request) {
        return ResponseEntity.ok(ApiResponse.ok("Insumo actualizado exitosamente",
                insumoService.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        insumoService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Insumo eliminado exitosamente", null));
    }
}