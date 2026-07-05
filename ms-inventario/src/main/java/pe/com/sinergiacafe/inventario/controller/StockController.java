package pe.com.sinergiacafe.inventario.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.inventario.dto.StockRequestDTO;
import pe.com.sinergiacafe.inventario.dto.StockResponseDTO;
import pe.com.sinergiacafe.inventario.service.StockService;
import pe.com.sinergiacafe.inventario.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<StockResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(stockService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StockResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(stockService.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<StockResponseDTO>> crear(
            @Valid @RequestBody StockRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Stock creado exitosamente",
                        stockService.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StockResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody StockRequestDTO request) {
        return ResponseEntity.ok(ApiResponse.ok("Stock actualizado exitosamente",
                stockService.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        stockService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Stock eliminado exitosamente", null));
    }
}