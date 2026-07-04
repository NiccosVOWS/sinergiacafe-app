package pe.com.sinergiacafe.ventas.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.ventas.dto.MetodoPagoResponseDTO;
import pe.com.sinergiacafe.ventas.entity.MetodoPagoEntity;
import pe.com.sinergiacafe.ventas.repository.MetodoPagoRepository;
import pe.com.sinergiacafe.ventas.shared.ApiResponse;
import pe.com.sinergiacafe.ventas.shared.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/metodos-pago")
@RequiredArgsConstructor
public class MetodoPagoController {

    private final MetodoPagoRepository metodoPagoRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<List<MetodoPagoResponseDTO>>> listarTodos() {
        List<MetodoPagoResponseDTO> lista = metodoPagoRepository.findAll()
                .stream()
                .map(e -> MetodoPagoResponseDTO.builder()
                        .idMetodoPago(e.getIdMetodoPago())
                        .nombre(e.getNombre())
                        .descripcion(e.getDescripcion())
                        .activo(e.getActivo())
                        .build())
                .toList();
        return ResponseEntity.ok(ApiResponse.ok(lista));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MetodoPagoResponseDTO>> obtenerPorId(
            @PathVariable Long id) {
        MetodoPagoEntity entity = metodoPagoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MetodoPago", id));
        return ResponseEntity.ok(ApiResponse.ok(MetodoPagoResponseDTO.builder()
                .idMetodoPago(entity.getIdMetodoPago())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .activo(entity.getActivo())
                .build()));
    }
}