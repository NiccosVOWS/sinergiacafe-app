package pe.com.sinergiacafe.clientes.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.clientes.dto.ClienteRequestDTO;
import pe.com.sinergiacafe.clientes.dto.ClienteResponseDTO;
import pe.com.sinergiacafe.clientes.service.ClienteService;
import pe.com.sinergiacafe.clientes.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClienteResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(clienteService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClienteResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(clienteService.obtenerPorId(id)));
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<ApiResponse<ClienteResponseDTO>> obtenerPorDni(@PathVariable String dni) {
        return ResponseEntity.ok(ApiResponse.ok(clienteService.obtenerPorDni(dni)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ClienteResponseDTO>> crear(
            @Valid @RequestBody ClienteRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Cliente creado exitosamente", clienteService.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ClienteResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ClienteRequestDTO request) {
        return ResponseEntity.ok(ApiResponse.ok("Cliente actualizado exitosamente",
                clienteService.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        clienteService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Cliente eliminado exitosamente", null));
    }
}