package pe.com.sinergiacafe.ventas.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.ventas.dto.CierreCajaRequestDTO;
import pe.com.sinergiacafe.ventas.dto.CierreCajaResponseDTO;
import pe.com.sinergiacafe.ventas.service.CierreCajaService;
import pe.com.sinergiacafe.ventas.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cierres-caja")
@RequiredArgsConstructor
public class CierreCajaController {

    private final CierreCajaService cierreCajaService;

    @GetMapping("/caja/{idCaja}")
    public ResponseEntity<ApiResponse<List<CierreCajaResponseDTO>>> listarPorCaja(
            @PathVariable Long idCaja) {
        return ResponseEntity.ok(ApiResponse.ok(cierreCajaService.listarPorCaja(idCaja)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CierreCajaResponseDTO>> registrar(
            @Valid @RequestBody CierreCajaRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Cierre de caja registrado exitosamente",
                        cierreCajaService.registrar(request)));
    }
}