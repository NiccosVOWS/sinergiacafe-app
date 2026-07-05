package pe.com.sinergiacafe.recursoshumanos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.recursoshumanos.dto.CargoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.CargoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.service.CargoService;
import pe.com.sinergiacafe.recursoshumanos.shared.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cargos")
@RequiredArgsConstructor
public class CargoController {

    private final CargoService cargoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CargoResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(ApiResponse.ok(cargoService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CargoResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(cargoService.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CargoResponseDTO>> crear(
            @Valid @RequestBody CargoRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Cargo creado exitosamente",
                        cargoService.crear(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CargoResponseDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody CargoRequestDTO request) {
        return ResponseEntity.ok(ApiResponse.ok("Cargo actualizado exitosamente",
                cargoService.actualizar(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        cargoService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Cargo eliminado exitosamente", null));
    }
}