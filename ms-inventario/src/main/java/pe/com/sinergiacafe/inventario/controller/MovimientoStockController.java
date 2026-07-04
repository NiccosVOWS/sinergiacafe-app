package pe.com.sinergiacafe.inventario.controller;

import pe.com.sinergiacafe.inventario.dto.MovimientoStockRequestDTO;
import pe.com.sinergiacafe.inventario.dto.MovimientoStockResponseDTO;
import pe.com.sinergiacafe.inventario.service.MovimientoStockService;
import pe.com.sinergiacafe.inventario.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movimientos-stock")
@RequiredArgsConstructor
public class MovimientoStockController {

    private final MovimientoStockService service;

    @GetMapping
    public ApiResponse<List<MovimientoStockResponseDTO>> findAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<MovimientoStockResponseDTO> findById(@PathVariable Long id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<MovimientoStockResponseDTO> create(@Valid @RequestBody MovimientoStockRequestDTO dto) {
        return ApiResponse.ok("MovimientoStock creado correctamente", service.create(dto));
    }

    @PutMapping("/{id}")
    public ApiResponse<MovimientoStockResponseDTO> update(@PathVariable Long id, @Valid @RequestBody MovimientoStockRequestDTO dto) {
        return ApiResponse.ok("MovimientoStock actualizado correctamente", service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok("MovimientoStock eliminado correctamente", null);
    }
}
