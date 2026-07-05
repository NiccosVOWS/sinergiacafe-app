package pe.com.sinergiacafe.mantenimiento.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.mantenimiento.dto.TipoEquipoRequestDTO;
import pe.com.sinergiacafe.mantenimiento.dto.TipoEquipoResponseDTO;
import pe.com.sinergiacafe.mantenimiento.service.TipoEquipoService;
import pe.com.sinergiacafe.mantenimiento.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tipos-equipo")
@RequiredArgsConstructor
public class TipoEquipoController {

    private final TipoEquipoService tipoEquipoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TipoEquipoResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(tipoEquipoService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TipoEquipoResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(tipoEquipoService.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TipoEquipoResponseDTO>> crear(
            @Valid @RequestBody TipoEquipoRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Tipo de equipo creado exitosamente",
                        tipoEquipoService.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TipoEquipoResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody TipoEquipoRequestDTO request) {
        return ResponseEntity.ok(ApiResponse.ok("Tipo de equipo actualizado exitosamente",
                tipoEquipoService.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        tipoEquipoService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Tipo de equipo eliminado exitosamente", null));
    }
}