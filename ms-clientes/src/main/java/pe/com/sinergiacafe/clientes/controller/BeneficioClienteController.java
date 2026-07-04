package pe.com.sinergiacafe.clientes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.clientes.dto.BeneficioClienteResponseDTO;
import pe.com.sinergiacafe.clientes.service.BeneficioClienteService;
import pe.com.sinergiacafe.clientes.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/beneficios-cliente")
@RequiredArgsConstructor
public class BeneficioClienteController {

    private final BeneficioClienteService beneficioClienteService;

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<ApiResponse<List<BeneficioClienteResponseDTO>>> listarPorCliente(
            @PathVariable Long idCliente) {
        return ResponseEntity.ok(ApiResponse.ok(
                beneficioClienteService.listarPorCliente(idCliente)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BeneficioClienteResponseDTO>> obtenerPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(beneficioClienteService.obtenerPorId(id)));
    }
}