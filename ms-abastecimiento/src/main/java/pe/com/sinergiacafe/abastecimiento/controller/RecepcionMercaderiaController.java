package pe.com.sinergiacafe.abastecimiento.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.abastecimiento.dto.RecepcionMercaderiaRequestDTO;
import pe.com.sinergiacafe.abastecimiento.dto.RecepcionMercaderiaResponseDTO;
import pe.com.sinergiacafe.abastecimiento.service.RecepcionMercaderiaService;
import pe.com.sinergiacafe.abastecimiento.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recepciones-mercaderia")
@RequiredArgsConstructor
public class RecepcionMercaderiaController {

    private final RecepcionMercaderiaService recepcionMercaderiaService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<RecepcionMercaderiaResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(recepcionMercaderiaService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RecepcionMercaderiaResponseDTO>> obtenerPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(recepcionMercaderiaService.obtenerPorId(id)));
    }

    @GetMapping("/orden/{idOrdenCompra}")
    public ResponseEntity<ApiResponse<List<RecepcionMercaderiaResponseDTO>>> listarPorOrden(
            @PathVariable Long idOrdenCompra) {
        return ResponseEntity.ok(ApiResponse.ok(
                recepcionMercaderiaService.listarPorOrden(idOrdenCompra)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RecepcionMercaderiaResponseDTO>> registrar(
            @Valid @RequestBody RecepcionMercaderiaRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Recepción registrada exitosamente",
                        recepcionMercaderiaService.registrar(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        recepcionMercaderiaService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Recepción eliminada exitosamente", null));
    }
}