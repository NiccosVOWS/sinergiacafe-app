package pe.com.sinergiacafe.produccion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.produccion.dto.CategoriaInsumoResponseDTO;
import pe.com.sinergiacafe.produccion.dto.InsumoResponseDTO;
import pe.com.sinergiacafe.produccion.dto.RecetaInsumoResponseDTO;
import pe.com.sinergiacafe.produccion.dto.RecetaRequestDTO;
import pe.com.sinergiacafe.produccion.dto.RecetaResponseDTO;
import pe.com.sinergiacafe.produccion.entity.RecetaEntity;
import pe.com.sinergiacafe.produccion.repository.RecetaInsumoRepository;
import pe.com.sinergiacafe.produccion.repository.RecetaRepository;
import pe.com.sinergiacafe.produccion.shared.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecetaServiceImpl implements RecetaService {

    private final RecetaRepository recetaRepository;
    private final RecetaInsumoRepository recetaInsumoRepository;

    @Override
    public List<RecetaResponseDTO> listarTodos() {
        return recetaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public RecetaResponseDTO obtenerPorId(Long id) {
        RecetaEntity receta = recetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receta", id));
        return toResponseDTO(receta);
    }

    @Override
    public RecetaResponseDTO obtenerPorProducto(Long idProducto) {
        RecetaEntity receta = recetaRepository.findByIdProducto(idProducto)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Receta para el producto con ID " + idProducto + " no encontrada"));
        return toResponseDTO(receta);
    }

    @Override
    public RecetaResponseDTO crear(RecetaRequestDTO request) {
        if (recetaRepository.existsByIdProducto(request.getIdProducto())) {
            throw new RuntimeException("Ya existe una receta para el producto con ID "
                    + request.getIdProducto());
        }
        RecetaEntity receta = RecetaEntity.builder()
                .idProducto(request.getIdProducto())
                .instrucciones(request.getInstrucciones())
                .tiempoPrepMin(request.getTiempoPrepMin())
                .build();
        return toResponseDTO(recetaRepository.save(receta));
    }

    @Override
    public RecetaResponseDTO actualizar(Long id, RecetaRequestDTO request) {
        RecetaEntity receta = recetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receta", id));
        receta.setInstrucciones(request.getInstrucciones());
        receta.setTiempoPrepMin(request.getTiempoPrepMin());
        return toResponseDTO(recetaRepository.save(receta));
    }

    @Override
    public void eliminar(Long id) {
        if (!recetaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Receta", id);
        }
        recetaRepository.deleteById(id);
    }

    private RecetaResponseDTO toResponseDTO(RecetaEntity entity) {
        List<RecetaInsumoResponseDTO> insumos = recetaInsumoRepository
                .findByRecetaIdReceta(entity.getIdReceta())
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

        return RecetaResponseDTO.builder()
                .idReceta(entity.getIdReceta())
                .idProducto(entity.getIdProducto())
                .instrucciones(entity.getInstrucciones())
                .tiempoPrepMin(entity.getTiempoPrepMin())
                .insumos(insumos)
                .build();
    }
}