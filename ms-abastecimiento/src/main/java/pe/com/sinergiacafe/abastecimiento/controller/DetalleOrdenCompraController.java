package pe.com.sinergiacafe.abastecimiento.controller;

import pe.com.sinergiacafe.abastecimiento.dto.DetalleOrdenCompraRequestDTO;
import pe.com.sinergiacafe.abastecimiento.dto.DetalleOrdenCompraResponseDTO;
import pe.com.sinergiacafe.abastecimiento.service.DetalleOrdenCompraService;
import pe.com.sinergiacafe.abastecimiento.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detalle-orden-compra")
@RequiredArgsConstructor
public class DetalleOrdenCompraController {

    private final DetalleOrdenCompraService service;

    @GetMapping
    public ApiResponse<List<DetalleOrdenCompraResponseDTO>> findAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<DetalleOrdenCompraResponseDTO> findById(@PathVariable Long id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<DetalleOrdenCompraResponseDTO> create(@Valid @RequestBody DetalleOrdenCompraRequestDTO dto) {
        return ApiResponse.ok("DetalleOrdenCompra creado correctamente", service.create(dto));
    }

    @PutMapping("/{id}")
    public ApiResponse<DetalleOrdenCompraResponseDTO> update(@PathVariable Long id, @Valid @RequestBody DetalleOrdenCompraRequestDTO dto) {
        return ApiResponse.ok("DetalleOrdenCompra actualizado correctamente", service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok("DetalleOrdenCompra eliminado correctamente", null);
    }
}
