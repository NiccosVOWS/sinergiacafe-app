package pe.com.sinergiacafe.recursoshumanos.controller;

import pe.com.sinergiacafe.recursoshumanos.dto.EvaluacionDesempenoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.EvaluacionDesempenoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.service.EvaluacionDesempenoService;
import pe.com.sinergiacafe.recursoshumanos.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/evaluaciones-desempeno")
@RequiredArgsConstructor
public class EvaluacionDesempenoController {

    private final EvaluacionDesempenoService service;

    @GetMapping
    public ApiResponse<List<EvaluacionDesempenoResponseDTO>> findAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<EvaluacionDesempenoResponseDTO> findById(@PathVariable Long id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<EvaluacionDesempenoResponseDTO> create(@Valid @RequestBody EvaluacionDesempenoRequestDTO dto) {
        return ApiResponse.ok("EvaluacionDesempeno creado correctamente", service.create(dto));
    }

    @PutMapping("/{id}")
    public ApiResponse<EvaluacionDesempenoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody EvaluacionDesempenoRequestDTO dto) {
        return ApiResponse.ok("EvaluacionDesempeno actualizado correctamente", service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok("EvaluacionDesempeno eliminado correctamente", null);
    }
}
