package pe.com.sinergiacafe.administracion.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.administracion.dto.GastoRequestDTO;
import pe.com.sinergiacafe.administracion.dto.GastoResponseDTO;
import pe.com.sinergiacafe.administracion.service.GastoService;
import pe.com.sinergiacafe.administracion.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gastos")
@RequiredArgsConstructor
public class GastoController {

    private final GastoService gastoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<GastoResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(gastoService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GastoResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(gastoService.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<GastoResponseDTO>> crear(
            @Valid @RequestBody GastoRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Gasto registrado exitosamente",
                        gastoService.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<GastoResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody GastoRequestDTO request) {

        return ResponseEntity.ok(ApiResponse.ok(
                "Gasto actualizado exitosamente",
                gastoService.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {

        gastoService.eliminar(id);

        return ResponseEntity.ok(ApiResponse.ok(
                "Gasto eliminado exitosamente",
                null));
    }
}