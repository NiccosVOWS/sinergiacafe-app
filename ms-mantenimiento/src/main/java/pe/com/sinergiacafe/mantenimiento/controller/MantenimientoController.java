package pe.com.sinergiacafe.mantenimiento.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.mantenimiento.dto.MantenimientoRequestDTO;
import pe.com.sinergiacafe.mantenimiento.dto.MantenimientoResponseDTO;
import pe.com.sinergiacafe.mantenimiento.service.MantenimientoService;
import pe.com.sinergiacafe.mantenimiento.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mantenimientos")
@RequiredArgsConstructor
public class MantenimientoController {

    private final MantenimientoService mantenimientoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<MantenimientoResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(mantenimientoService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MantenimientoResponseDTO>> obtenerPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(mantenimientoService.obtenerPorId(id)));
    }

    @GetMapping("/equipo/{idEquipo}")
    public ResponseEntity<ApiResponse<List<MantenimientoResponseDTO>>> listarPorEquipo(
            @PathVariable Long idEquipo) {
        return ResponseEntity.ok(ApiResponse.ok(mantenimientoService.listarPorEquipo(idEquipo)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MantenimientoResponseDTO>> registrar(
            @Valid @RequestBody MantenimientoRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Mantenimiento registrado exitosamente",
                        mantenimientoService.registrar(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        mantenimientoService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Mantenimiento eliminado exitosamente", null));
    }
}