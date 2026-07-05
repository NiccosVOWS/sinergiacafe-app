package pe.com.sinergiacafe.mantenimiento.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.mantenimiento.dto.EquipoRequestDTO;
import pe.com.sinergiacafe.mantenimiento.dto.EquipoResponseDTO;
import pe.com.sinergiacafe.mantenimiento.service.EquipoService;
import pe.com.sinergiacafe.mantenimiento.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/equipos")
@RequiredArgsConstructor
public class EquipoController {

    private final EquipoService equipoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<EquipoResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(equipoService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EquipoResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(equipoService.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EquipoResponseDTO>> crear(
            @Valid @RequestBody EquipoRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Equipo creado exitosamente",
                        equipoService.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EquipoResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody EquipoRequestDTO request) {
        return ResponseEntity.ok(ApiResponse.ok("Equipo actualizado exitosamente",
                equipoService.actualizar(id, request)));
    }

    @PatchMapping("/{id}/estado/{estado}")
    public ResponseEntity<ApiResponse<EquipoResponseDTO>> actualizarEstado(
            @PathVariable Long id,
            @PathVariable String estado) {
        return ResponseEntity.ok(ApiResponse.ok("Estado actualizado exitosamente",
                equipoService.actualizarEstado(id, estado)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        equipoService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Equipo eliminado exitosamente", null));
    }
}