package pe.com.sinergiacafe.ventas.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.ventas.dto.ComprobanteRequestDTO;
import pe.com.sinergiacafe.ventas.dto.ComprobanteResponseDTO;
import pe.com.sinergiacafe.ventas.service.ComprobanteService;
import pe.com.sinergiacafe.ventas.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comprobantes")
@RequiredArgsConstructor
public class ComprobanteController {

    private final ComprobanteService comprobanteService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ComprobanteResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(comprobanteService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ComprobanteResponseDTO>> obtenerPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(comprobanteService.obtenerPorId(id)));
    }

    @GetMapping("/pedido/{idPedido}")
    public ResponseEntity<ApiResponse<List<ComprobanteResponseDTO>>> listarPorPedido(
            @PathVariable Long idPedido) {
        return ResponseEntity.ok(ApiResponse.ok(comprobanteService.listarPorPedido(idPedido)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ComprobanteResponseDTO>> emitir(
            @Valid @RequestBody ComprobanteRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Comprobante emitido exitosamente",
                        comprobanteService.emitir(request)));
    }
}