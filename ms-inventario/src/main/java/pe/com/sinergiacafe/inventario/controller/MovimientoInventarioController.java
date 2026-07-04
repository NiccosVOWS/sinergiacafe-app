package pe.com.sinergiacafe.inventario.controller;

import pe.com.sinergiacafe.inventario.dto.MovimientoInventarioRequestDTO;
import pe.com.sinergiacafe.inventario.dto.MovimientoInventarioResponseDTO;
import pe.com.sinergiacafe.inventario.service.MovimientoInventarioService;
import pe.com.sinergiacafe.inventario.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movimientos-inventario")
@RequiredArgsConstructor
public class MovimientoInventarioController {

    private final MovimientoInventarioService service;

    @GetMapping
    public ApiResponse<List<MovimientoInventarioResponseDTO>> findAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<MovimientoInventarioResponseDTO> findById(@PathVariable Long id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<MovimientoInventarioResponseDTO> create(@Valid @RequestBody MovimientoInventarioRequestDTO dto) {
        return ApiResponse.ok("MovimientoInventario creado correctamente", service.create(dto));
    }

    @PutMapping("/{id}")
    public ApiResponse<MovimientoInventarioResponseDTO> update(@PathVariable Long id, @Valid @RequestBody MovimientoInventarioRequestDTO dto) {
        return ApiResponse.ok("MovimientoInventario actualizado correctamente", service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok("MovimientoInventario eliminado correctamente", null);
    }
}
