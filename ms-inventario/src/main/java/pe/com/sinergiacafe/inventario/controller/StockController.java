package pe.com.sinergiacafe.inventario.controller;

import pe.com.sinergiacafe.inventario.dto.StockRequestDTO;
import pe.com.sinergiacafe.inventario.dto.StockResponseDTO;
import pe.com.sinergiacafe.inventario.service.StockService;
import pe.com.sinergiacafe.inventario.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService service;

    @GetMapping
    public ApiResponse<List<StockResponseDTO>> findAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<StockResponseDTO> findById(@PathVariable Long id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<StockResponseDTO> create(@Valid @RequestBody StockRequestDTO dto) {
        return ApiResponse.ok("Stock creado correctamente", service.create(dto));
    }

    @PutMapping("/{id}")
    public ApiResponse<StockResponseDTO> update(@PathVariable Long id, @Valid @RequestBody StockRequestDTO dto) {
        return ApiResponse.ok("Stock actualizado correctamente", service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok("Stock eliminado correctamente", null);
    }
}
