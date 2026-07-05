package pe.com.sinergiacafe.inventario.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.inventario.dto.MovimientoStockRequestDTO;
import pe.com.sinergiacafe.inventario.dto.MovimientoStockResponseDTO;
import pe.com.sinergiacafe.inventario.service.MovimientoStockService;
import pe.com.sinergiacafe.inventario.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movimientos-stock")
@RequiredArgsConstructor
public class MovimientoStockController {

    private final MovimientoStockService movimientoStockService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<MovimientoStockResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(movimientoStockService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MovimientoStockResponseDTO>> obtenerPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(movimientoStockService.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MovimientoStockResponseDTO>> crear(
            @Valid @RequestBody MovimientoStockRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Movimiento registrado exitosamente",
                        movimientoStockService.crear(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        movimientoStockService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Movimiento eliminado exitosamente", null));
    }
}