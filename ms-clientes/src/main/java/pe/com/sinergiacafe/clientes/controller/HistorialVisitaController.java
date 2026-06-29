package pe.com.sinergiacafe.clientes.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.clientes.dto.HistorialVisitaRequestDTO;
import pe.com.sinergiacafe.clientes.dto.HistorialVisitaResponseDTO;
import pe.com.sinergiacafe.clientes.entity.ClienteEntity;
import pe.com.sinergiacafe.clientes.entity.HistorialVisitaEntity;
import pe.com.sinergiacafe.clientes.repository.ClienteRepository;
import pe.com.sinergiacafe.clientes.repository.HistorialVisitaRepository;
import pe.com.sinergiacafe.clientes.shared.ApiResponse;
import pe.com.sinergiacafe.clientes.shared.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/historial-visitas")
@RequiredArgsConstructor
public class HistorialVisitaController {

    private final HistorialVisitaRepository historialVisitaRepository;
    private final ClienteRepository clienteRepository;

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<ApiResponse<List<HistorialVisitaResponseDTO>>> listarPorCliente(
            @PathVariable Long idCliente) {
        List<HistorialVisitaResponseDTO> lista = historialVisitaRepository
                .findByClienteIdCliente(idCliente)
                .stream()
                .map(this::toResponseDTO)
                .toList();
        return ResponseEntity.ok(ApiResponse.ok(lista));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<HistorialVisitaResponseDTO>> registrar(
            @Valid @RequestBody HistorialVisitaRequestDTO request) {
        ClienteEntity cliente = clienteRepository.findById(request.getIdCliente())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", request.getIdCliente()));

        HistorialVisitaEntity entity = HistorialVisitaEntity.builder()
                .cliente(cliente)
                .consumoTotal(request.getConsumoTotal())
                .observaciones(request.getObservaciones())
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Visita registrada exitosamente",
                        toResponseDTO(historialVisitaRepository.save(entity))));
    }

    private HistorialVisitaResponseDTO toResponseDTO(HistorialVisitaEntity entity) {
        return HistorialVisitaResponseDTO.builder()
                .idHistorialVisita(entity.getIdHistorialVisita())
                .idCliente(entity.getCliente().getIdCliente())
                .nombreCliente(entity.getCliente().getNombres() + " " + entity.getCliente().getApellidos())
                .fechaVisita(entity.getFechaVisita())
                .consumoTotal(entity.getConsumoTotal())
                .observaciones(entity.getObservaciones())
                .build();
    }
}