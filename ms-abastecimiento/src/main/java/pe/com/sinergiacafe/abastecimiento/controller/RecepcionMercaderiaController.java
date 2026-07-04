package pe.com.sinergiacafe.abastecimiento.controller;

import pe.com.sinergiacafe.abastecimiento.dto.RecepcionMercaderiaRequestDTO;
import pe.com.sinergiacafe.abastecimiento.dto.RecepcionMercaderiaResponseDTO;
import pe.com.sinergiacafe.abastecimiento.service.RecepcionMercaderiaService;
import pe.com.sinergiacafe.abastecimiento.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recepciones-mercaderia")
@RequiredArgsConstructor
public class RecepcionMercaderiaController {

    private final RecepcionMercaderiaService service;

    @GetMapping
    public ApiResponse<List<RecepcionMercaderiaResponseDTO>> findAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<RecepcionMercaderiaResponseDTO> findById(@PathVariable Long id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<RecepcionMercaderiaResponseDTO> create(@Valid @RequestBody RecepcionMercaderiaRequestDTO dto) {
        return ApiResponse.ok("RecepcionMercaderia creado correctamente", service.create(dto));
    }

    @PutMapping("/{id}")
    public ApiResponse<RecepcionMercaderiaResponseDTO> update(@PathVariable Long id, @Valid @RequestBody RecepcionMercaderiaRequestDTO dto) {
        return ApiResponse.ok("RecepcionMercaderia actualizado correctamente", service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok("RecepcionMercaderia eliminado correctamente", null);
    }
}
