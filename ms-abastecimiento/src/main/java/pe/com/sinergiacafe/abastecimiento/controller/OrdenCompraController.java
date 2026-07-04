package pe.com.sinergiacafe.abastecimiento.controller;

import pe.com.sinergiacafe.abastecimiento.dto.OrdenCompraRequestDTO;
import pe.com.sinergiacafe.abastecimiento.dto.OrdenCompraResponseDTO;
import pe.com.sinergiacafe.abastecimiento.service.OrdenCompraService;
import pe.com.sinergiacafe.abastecimiento.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ordenes-compra")
@RequiredArgsConstructor
public class OrdenCompraController {

    private final OrdenCompraService service;

    @GetMapping
    public ApiResponse<List<OrdenCompraResponseDTO>> findAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<OrdenCompraResponseDTO> findById(@PathVariable Long id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<OrdenCompraResponseDTO> create(@Valid @RequestBody OrdenCompraRequestDTO dto) {
        return ApiResponse.ok("OrdenCompra creado correctamente", service.create(dto));
    }

    @PutMapping("/{id}")
    public ApiResponse<OrdenCompraResponseDTO> update(@PathVariable Long id, @Valid @RequestBody OrdenCompraRequestDTO dto) {
        return ApiResponse.ok("OrdenCompra actualizado correctamente", service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok("OrdenCompra eliminado correctamente", null);
    }
}
