package pe.com.sinergiacafe.clientes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.clientes.dto.ProgramaFidelizacionResponseDTO;
import pe.com.sinergiacafe.clientes.service.ProgramaFidelizacionService;
import pe.com.sinergiacafe.clientes.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/programas-fidelizacion")
@RequiredArgsConstructor
public class ProgramaFidelizacionController {

    private final ProgramaFidelizacionService programaFidelizacionService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProgramaFidelizacionResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(programaFidelizacionService.listarTodos()));
    }

    @GetMapping("/activos")
    public ResponseEntity<ApiResponse<List<ProgramaFidelizacionResponseDTO>>> listarActivos() {
        return ResponseEntity.ok(ApiResponse.ok(programaFidelizacionService.listarActivos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProgramaFidelizacionResponseDTO>> obtenerPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(programaFidelizacionService.obtenerPorId(id)));
    }
}