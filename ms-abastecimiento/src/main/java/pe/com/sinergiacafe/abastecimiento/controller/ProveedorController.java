package pe.com.sinergiacafe.abastecimiento.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.abastecimiento.dto.ProveedorRequestDTO;
import pe.com.sinergiacafe.abastecimiento.dto.ProveedorResponseDTO;
import pe.com.sinergiacafe.abastecimiento.service.ProveedorService;
import pe.com.sinergiacafe.abastecimiento.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proveedores")
@RequiredArgsConstructor
public class ProveedorController {

    private final ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProveedorResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(proveedorService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProveedorResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(proveedorService.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProveedorResponseDTO>> crear(
            @Valid @RequestBody ProveedorRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Proveedor creado exitosamente",
                        proveedorService.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProveedorResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProveedorRequestDTO request) {
        return ResponseEntity.ok(ApiResponse.ok("Proveedor actualizado exitosamente",
                proveedorService.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        proveedorService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Proveedor eliminado exitosamente", null));
    }
}