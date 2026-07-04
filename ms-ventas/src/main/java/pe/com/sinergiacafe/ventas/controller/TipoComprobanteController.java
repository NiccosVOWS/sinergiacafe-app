package pe.com.sinergiacafe.ventas.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.ventas.dto.TipoComprobanteResponseDTO;
import pe.com.sinergiacafe.ventas.entity.TipoComprobanteEntity;
import pe.com.sinergiacafe.ventas.repository.TipoComprobanteRepository;
import pe.com.sinergiacafe.ventas.shared.ApiResponse;
import pe.com.sinergiacafe.ventas.shared.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tipos-comprobante")
@RequiredArgsConstructor
public class TipoComprobanteController {

    private final TipoComprobanteRepository tipoComprobanteRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TipoComprobanteResponseDTO>>> listarTodos() {
        List<TipoComprobanteResponseDTO> lista = tipoComprobanteRepository.findAll()
                .stream()
                .map(e -> TipoComprobanteResponseDTO.builder()
                        .idTipoComprobante(e.getIdTipoComprobante())
                        .nombre(e.getNombre())
                        .descripcion(e.getDescripcion())
                        .build())
                .toList();
        return ResponseEntity.ok(ApiResponse.ok(lista));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TipoComprobanteResponseDTO>> obtenerPorId(
            @PathVariable Long id) {
        TipoComprobanteEntity entity = tipoComprobanteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoComprobante", id));
        return ResponseEntity.ok(ApiResponse.ok(TipoComprobanteResponseDTO.builder()
                .idTipoComprobante(entity.getIdTipoComprobante())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build()));
    }
}