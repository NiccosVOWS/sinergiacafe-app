package pe.com.sinergiacafe.abastecimiento.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.abastecimiento.dto.ContratoProveedorRequestDTO;
import pe.com.sinergiacafe.abastecimiento.dto.ContratoProveedorResponseDTO;
import pe.com.sinergiacafe.abastecimiento.service.ContratoProveedorService;
import pe.com.sinergiacafe.abastecimiento.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contratos-proveedor")
@RequiredArgsConstructor
public class ContratoProveedorController {

    private final ContratoProveedorService contratoProveedorService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ContratoProveedorResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(contratoProveedorService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ContratoProveedorResponseDTO>> obtenerPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(contratoProveedorService.obtenerPorId(id)));
    }

    @GetMapping("/proveedor/{idProveedor}")
    public ResponseEntity<ApiResponse<List<ContratoProveedorResponseDTO>>> listarPorProveedor(
            @PathVariable Long idProveedor) {
        return ResponseEntity.ok(ApiResponse.ok(
                contratoProveedorService.listarPorProveedor(idProveedor)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ContratoProveedorResponseDTO>> crear(
            @Valid @RequestBody ContratoProveedorRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Contrato creado exitosamente",
                        contratoProveedorService.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ContratoProveedorResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ContratoProveedorRequestDTO request) {
        return ResponseEntity.ok(ApiResponse.ok("Contrato actualizado exitosamente",
                contratoProveedorService.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        contratoProveedorService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Contrato eliminado exitosamente", null));
    }
}