package pe.com.sinergiacafe.produccion.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.produccion.dto.RecetaInsumoRequestDTO;
import pe.com.sinergiacafe.produccion.dto.RecetaInsumoResponseDTO;
import pe.com.sinergiacafe.produccion.entity.InsumoEntity;
import pe.com.sinergiacafe.produccion.entity.RecetaEntity;
import pe.com.sinergiacafe.produccion.entity.RecetaInsumoEntity;
import pe.com.sinergiacafe.produccion.entity.RecetaInsumoId;
import pe.com.sinergiacafe.produccion.repository.InsumoRepository;
import pe.com.sinergiacafe.produccion.repository.RecetaInsumoRepository;
import pe.com.sinergiacafe.produccion.repository.RecetaRepository;
import pe.com.sinergiacafe.produccion.shared.ApiResponse;
import pe.com.sinergiacafe.produccion.shared.ResourceNotFoundException;
import pe.com.sinergiacafe.produccion.dto.CategoriaInsumoResponseDTO;
import pe.com.sinergiacafe.produccion.dto.InsumoResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/v1/receta-insumos")
@RequiredArgsConstructor
public class RecetaInsumoController {

    private final RecetaInsumoRepository recetaInsumoRepository;
    private final RecetaRepository recetaRepository;
    private final InsumoRepository insumoRepository;

    @GetMapping("/receta/{idReceta}")
    public ResponseEntity<ApiResponse<List<RecetaInsumoResponseDTO>>> listarPorReceta(
            @PathVariable Long idReceta) {
        List<RecetaInsumoResponseDTO> lista = recetaInsumoRepository
                .findByRecetaIdReceta(idReceta)
                .stream()
                .map(ri -> RecetaInsumoResponseDTO.builder()
                        .idReceta(ri.getReceta().getIdReceta())
                        .insumo(InsumoResponseDTO.builder()
                                .idInsumo(ri.getInsumo().getIdInsumo())
                                .categoriaInsumo(CategoriaInsumoResponseDTO.builder()
                                        .idCategoriaInsumo(ri.getInsumo().getCategoriaInsumo().getIdCategoriaInsumo())
                                        .nombre(ri.getInsumo().getCategoriaInsumo().getNombre())
                                        .descripcion(ri.getInsumo().getCategoriaInsumo().getDescripcion())
                                        .build())
                                .nombre(ri.getInsumo().getNombre())
                                .unidadMedida(ri.getInsumo().getUnidadMedida())
                                .activo(ri.getInsumo().getActivo())
                                .build())
                        .cantidad(ri.getCantidad())
                        .build())
                .toList();
        return ResponseEntity.ok(ApiResponse.ok(lista));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RecetaInsumoResponseDTO>> agregar(
            @Valid @RequestBody RecetaInsumoRequestDTO request) {
        RecetaEntity receta = recetaRepository.findById(request.getIdReceta())
                .orElseThrow(() -> new ResourceNotFoundException("Receta", request.getIdReceta()));
        InsumoEntity insumo = insumoRepository.findById(request.getIdInsumo())
                .orElseThrow(() -> new ResourceNotFoundException("Insumo", request.getIdInsumo()));

        RecetaInsumoId id = new RecetaInsumoId(request.getIdReceta(), request.getIdInsumo());
        RecetaInsumoEntity entity = RecetaInsumoEntity.builder()
                .id(id)
                .receta(receta)
                .insumo(insumo)
                .cantidad(request.getCantidad())
                .build();

        RecetaInsumoEntity saved = recetaInsumoRepository.save(entity);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Insumo agregado a receta exitosamente",
                        RecetaInsumoResponseDTO.builder()
                                .idReceta(saved.getReceta().getIdReceta())
                                .insumo(InsumoResponseDTO.builder()
                                        .idInsumo(saved.getInsumo().getIdInsumo())
                                        .categoriaInsumo(CategoriaInsumoResponseDTO.builder()
                                                .idCategoriaInsumo(saved.getInsumo().getCategoriaInsumo().getIdCategoriaInsumo())
                                                .nombre(saved.getInsumo().getCategoriaInsumo().getNombre())
                                                .descripcion(saved.getInsumo().getCategoriaInsumo().getDescripcion())
                                                .build())
                                        .nombre(saved.getInsumo().getNombre())
                                        .unidadMedida(saved.getInsumo().getUnidadMedida())
                                        .activo(saved.getInsumo().getActivo())
                                        .build())
                                .cantidad(saved.getCantidad())
                                .build()));
    }

    @DeleteMapping("/receta/{idReceta}/insumo/{idInsumo}")
    public ResponseEntity<ApiResponse<Void>> eliminar(
            @PathVariable Long idReceta,
            @PathVariable Long idInsumo) {
        RecetaInsumoId id = new RecetaInsumoId(idReceta, idInsumo);
        if (!recetaInsumoRepository.existsById(id)) {
            throw new ResourceNotFoundException("RecetaInsumo no encontrado");
        }
        recetaInsumoRepository.deleteById(id);
        return ResponseEntity.ok(ApiResponse.ok("Insumo eliminado de receta exitosamente", null));
    }
}