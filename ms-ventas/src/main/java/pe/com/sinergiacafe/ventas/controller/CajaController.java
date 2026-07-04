package pe.com.sinergiacafe.ventas.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.ventas.dto.CajaRequestDTO;
import pe.com.sinergiacafe.ventas.dto.CajaResponseDTO;
import pe.com.sinergiacafe.ventas.service.CajaService;
import pe.com.sinergiacafe.ventas.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cajas")
@RequiredArgsConstructor
public class CajaController {

    private final CajaService cajaService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CajaResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(cajaService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CajaResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(cajaService.obtenerPorId(id)));
    }

    @PostMapping("/abrir")
    public ResponseEntity<ApiResponse<CajaResponseDTO>> abrir(
            @Valid @RequestBody CajaRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Caja abierta exitosamente", cajaService.abrir(request)));
    }

    @PatchMapping("/{id}/cerrar")
    public ResponseEntity<ApiResponse<CajaResponseDTO>> cerrar(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("Caja cerrada exitosamente",
                cajaService.cerrar(id)));
    }
}