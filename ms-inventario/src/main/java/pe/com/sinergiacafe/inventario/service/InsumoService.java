package pe.com.sinergiacafe.inventario.service;

import pe.com.sinergiacafe.inventario.dto.InsumoRequestDTO;
import pe.com.sinergiacafe.inventario.dto.InsumoResponseDTO;
import pe.com.sinergiacafe.inventario.entity.InsumoEntity;
import pe.com.sinergiacafe.inventario.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.inventario.repository.InsumoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsumoService {

    private final InsumoRepository repository;

    public List<InsumoResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public InsumoResponseDTO findById(Long id) {
        InsumoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Insumo no encontrado con id: " + id));
        return toResponse(entity);
    }

    public InsumoResponseDTO create(InsumoRequestDTO dto) {
        InsumoEntity entity = new InsumoEntity();
        entity.setNombre(dto.getNombre());
        entity.setUnidadMedida(dto.getUnidadMedida());
        entity.setCategoria(dto.getCategoria());
        entity.setStockActual(dto.getStockActual());
        entity.setStockMinimo(dto.getStockMinimo());
        return toResponse(repository.save(entity));
    }

    public InsumoResponseDTO update(Long id, InsumoRequestDTO dto) {
        InsumoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Insumo no encontrado con id: " + id));
        entity.setNombre(dto.getNombre());
        entity.setUnidadMedida(dto.getUnidadMedida());
        entity.setCategoria(dto.getCategoria());
        entity.setStockActual(dto.getStockActual());
        entity.setStockMinimo(dto.getStockMinimo());
        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Insumo no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }

    private InsumoResponseDTO toResponse(InsumoEntity entity) {
        return InsumoResponseDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .unidadMedida(entity.getUnidadMedida())
                .categoria(entity.getCategoria())
                .stockActual(entity.getStockActual())
                .stockMinimo(entity.getStockMinimo())
                .build();
    }
}
