package pe.com.sinergiacafe.marketing.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.marketing.dto.CampanaMarketingRequestDTO;
import pe.com.sinergiacafe.marketing.dto.CampanaMarketingResponseDTO;
import pe.com.sinergiacafe.marketing.service.CampanaMarketingService;
import pe.com.sinergiacafe.marketing.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/campanas-marketing")
@RequiredArgsConstructor
public class CampanaMarketingController {

    private final CampanaMarketingService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CampanaMarketingResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(service.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CampanaMarketingResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(service.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CampanaMarketingResponseDTO>> crear(
            @Valid @RequestBody CampanaMarketingRequestDTO request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Campaña de marketing creada exitosamente",
                        service.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CampanaMarketingResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody CampanaMarketingRequestDTO request) {

        return ResponseEntity.ok(ApiResponse.ok(
                "Campaña de marketing actualizada exitosamente",
                service.actualizar(id, request)
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.ok(ApiResponse.ok(
                "Campaña de marketing eliminada exitosamente",
                null
        ));
    }

}