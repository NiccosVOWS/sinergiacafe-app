package pe.com.sinergiacafe.mantenimiento.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.mantenimiento.dto.TipoMantenimientoRequestDTO;
import pe.com.sinergiacafe.mantenimiento.dto.TipoMantenimientoResponseDTO;
import pe.com.sinergiacafe.mantenimiento.service.TipoMantenimientoService;
import pe.com.sinergiacafe.mantenimiento.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tipos-mantenimiento")
@RequiredArgsConstructor
public class TipoMantenimientoController {

    private final TipoMantenimientoService tipoMantenimientoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TipoMantenimientoResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(tipoMantenimientoService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TipoMantenimientoResponseDTO>> obtenerPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(tipoMantenimientoService.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TipoMantenimientoResponseDTO>> crear(
            @Valid @RequestBody TipoMantenimientoRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Tipo de mantenimiento creado exitosamente",
                        tipoMantenimientoService.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TipoMantenimientoResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody TipoMantenimientoRequestDTO request) {
        return ResponseEntity.ok(ApiResponse.ok("Tipo de mantenimiento actualizado exitosamente",
                tipoMantenimientoService.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        tipoMantenimientoService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Tipo de mantenimiento eliminado exitosamente", null));
    }
}