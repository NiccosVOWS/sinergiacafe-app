package pe.com.sinergiacafe.administracion.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.administracion.dto.DocumentoAdministrativoRequestDTO;
import pe.com.sinergiacafe.administracion.dto.DocumentoAdministrativoResponseDTO;
import pe.com.sinergiacafe.administracion.service.DocumentoAdministrativoService;
import pe.com.sinergiacafe.administracion.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/documentos-administrativos")
@RequiredArgsConstructor
public class DocumentoAdministrativoController {

    private final DocumentoAdministrativoService documentoAdministrativoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<DocumentoAdministrativoResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(documentoAdministrativoService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DocumentoAdministrativoResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(documentoAdministrativoService.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DocumentoAdministrativoResponseDTO>> crear(
            @Valid @RequestBody DocumentoAdministrativoRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Documento administrativo creado exitosamente",
                        documentoAdministrativoService.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DocumentoAdministrativoResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody DocumentoAdministrativoRequestDTO request) {

        return ResponseEntity.ok(ApiResponse.ok(
                "Documento administrativo actualizado exitosamente",
                documentoAdministrativoService.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {

        documentoAdministrativoService.eliminar(id);

        return ResponseEntity.ok(ApiResponse.ok(
                "Documento administrativo eliminado exitosamente",
                null));
    }
}