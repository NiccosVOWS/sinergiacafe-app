package pe.com.sinergiacafe.recursoshumanos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.recursoshumanos.dto.AuditoriaRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.AuditoriaResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.service.AuditoriaService;
import pe.com.sinergiacafe.recursoshumanos.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auditorias")
@RequiredArgsConstructor
public class AuditoriaController {

    private final AuditoriaService auditoriaService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<AuditoriaResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(auditoriaService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AuditoriaResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(auditoriaService.obtenerPorId(id)));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<ApiResponse<List<AuditoriaResponseDTO>>> listarPorUsuario(
            @PathVariable Long idUsuario) {
        return ResponseEntity.ok(ApiResponse.ok(auditoriaService.listarPorUsuario(idUsuario)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AuditoriaResponseDTO>> registrar(
            @Valid @RequestBody AuditoriaRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Auditoría registrada exitosamente",
                        auditoriaService.registrar(request)));
    }
}