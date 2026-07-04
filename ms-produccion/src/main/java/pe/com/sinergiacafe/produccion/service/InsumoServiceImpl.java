package pe.com.sinergiacafe.produccion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.produccion.dto.CategoriaInsumoResponseDTO;
import pe.com.sinergiacafe.produccion.dto.InsumoRequestDTO;
import pe.com.sinergiacafe.produccion.dto.InsumoResponseDTO;
import pe.com.sinergiacafe.produccion.entity.CategoriaInsumoEntity;
import pe.com.sinergiacafe.produccion.entity.InsumoEntity;
import pe.com.sinergiacafe.produccion.repository.CategoriaInsumoRepository;
import pe.com.sinergiacafe.produccion.repository.InsumoRepository;
import pe.com.sinergiacafe.produccion.shared.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsumoServiceImpl implements InsumoService {

    private final InsumoRepository insumoRepository;
    private final CategoriaInsumoRepository categoriaInsumoRepository;

    @Override
    public List<InsumoResponseDTO> listarTodos() {
        return insumoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public InsumoResponseDTO obtenerPorId(Long id) {
        InsumoEntity insumo = insumoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Insumo", id));
        return toResponseDTO(insumo);
    }

    @Override
    public InsumoResponseDTO crear(InsumoRequestDTO request) {
        CategoriaInsumoEntity categoria = categoriaInsumoRepository
                .findById(request.getIdCategoriaInsumo())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "CategoriaInsumo", request.getIdCategoriaInsumo()));
        InsumoEntity insumo = InsumoEntity.builder()
                .categoriaInsumo(categoria)
                .nombre(request.getNombre())
                .unidadMedida(request.getUnidadMedida())
                .build();
        return toResponseDTO(insumoRepository.save(insumo));
    }

    @Override
    public InsumoResponseDTO actualizar(Long id, InsumoRequestDTO request) {
        InsumoEntity insumo = insumoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Insumo", id));
        CategoriaInsumoEntity categoria = categoriaInsumoRepository
                .findById(request.getIdCategoriaInsumo())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "CategoriaInsumo", request.getIdCategoriaInsumo()));
        insumo.setCategoriaInsumo(categoria);
        insumo.setNombre(request.getNombre());
        insumo.setUnidadMedida(request.getUnidadMedida());
        return toResponseDTO(insumoRepository.save(insumo));
    }

    @Override
    public void eliminar(Long id) {
        if (!insumoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Insumo", id);
        }
        insumoRepository.deleteById(id);
    }

    private InsumoResponseDTO toResponseDTO(InsumoEntity entity) {
        return InsumoResponseDTO.builder()
                .idInsumo(entity.getIdInsumo())
                .categoriaInsumo(CategoriaInsumoResponseDTO.builder()
                        .idCategoriaInsumo(entity.getCategoriaInsumo().getIdCategoriaInsumo())
                        .nombre(entity.getCategoriaInsumo().getNombre())
                        .descripcion(entity.getCategoriaInsumo().getDescripcion())
                        .build())
                .nombre(entity.getNombre())
                .unidadMedida(entity.getUnidadMedida())
                .activo(entity.getActivo())
                .build();
    }
}