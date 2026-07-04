package pe.com.sinergiacafe.marketing.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.marketing.dto.PromocionRequestDTO;
import pe.com.sinergiacafe.marketing.dto.PromocionResponseDTO;
import pe.com.sinergiacafe.marketing.service.PromocionService;
import pe.com.sinergiacafe.marketing.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/promociones")
@RequiredArgsConstructor
public class PromocionController {

    private final PromocionService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PromocionResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(service.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PromocionResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(service.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PromocionResponseDTO>> crear(
            @Valid @RequestBody PromocionRequestDTO request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Promoción creada exitosamente",
                        service.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PromocionResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody PromocionRequestDTO request) {

        return ResponseEntity.ok(ApiResponse.ok(
                "Promoción actualizada exitosamente",
                service.actualizar(id, request)
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.ok(ApiResponse.ok(
                "Promoción eliminada exitosamente",
                null
        ));
    }

}