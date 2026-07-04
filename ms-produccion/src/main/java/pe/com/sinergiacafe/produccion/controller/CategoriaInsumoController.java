package pe.com.sinergiacafe.produccion.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.sinergiacafe.produccion.dto.CategoriaInsumoResponseDTO;
import pe.com.sinergiacafe.produccion.entity.CategoriaInsumoEntity;
import pe.com.sinergiacafe.produccion.repository.CategoriaInsumoRepository;
import pe.com.sinergiacafe.produccion.shared.ApiResponse;
import pe.com.sinergiacafe.produccion.shared.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias-insumo")
@RequiredArgsConstructor
public class CategoriaInsumoController {

    private final CategoriaInsumoRepository categoriaInsumoRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoriaInsumoResponseDTO>>> listarTodos() {
        List<CategoriaInsumoResponseDTO> lista = categoriaInsumoRepository.findAll()
                .stream()
                .map(e -> CategoriaInsumoResponseDTO.builder()
                        .idCategoriaInsumo(e.getIdCategoriaInsumo())
                        .nombre(e.getNombre())
                        .descripcion(e.getDescripcion())
                        .build())
                .toList();
        return ResponseEntity.ok(ApiResponse.ok(lista));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoriaInsumoResponseDTO>> obtenerPorId(
            @PathVariable Long id) {
        CategoriaInsumoEntity entity = categoriaInsumoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CategoriaInsumo", id));
        return ResponseEntity.ok(ApiResponse.ok(CategoriaInsumoResponseDTO.builder()
                .idCategoriaInsumo(entity.getIdCategoriaInsumo())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build()));
    }
}