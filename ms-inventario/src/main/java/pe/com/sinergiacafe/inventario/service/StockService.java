package pe.com.sinergiacafe.inventario.service;

import pe.com.sinergiacafe.inventario.dto.StockRequestDTO;
import pe.com.sinergiacafe.inventario.dto.StockResponseDTO;
import pe.com.sinergiacafe.inventario.entity.StockEntity;
import pe.com.sinergiacafe.inventario.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.inventario.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository repository;

    public List<StockResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public StockResponseDTO findById(Long id) {
        StockEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock no encontrado con id: " + id));
        return toResponse(entity);
    }

    public StockResponseDTO create(StockRequestDTO dto) {
        StockEntity entity = new StockEntity();
        entity.setIdInsumo(dto.getIdInsumo());
        entity.setCantidadActual(dto.getCantidadActual());
        entity.setCantidadMinima(dto.getCantidadMinima());
        entity.setUltimaActualizacion(java.time.LocalDateTime.now());
        return toResponse(repository.save(entity));
    }

    public StockResponseDTO update(Long id, StockRequestDTO dto) {
        StockEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock no encontrado con id: " + id));
        entity.setIdInsumo(dto.getIdInsumo());
        entity.setCantidadActual(dto.getCantidadActual());
        entity.setCantidadMinima(dto.getCantidadMinima());
        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Stock no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }

    private StockResponseDTO toResponse(StockEntity entity) {
        return StockResponseDTO.builder()
                .id(entity.getId())
                .idInsumo(entity.getIdInsumo())
                .cantidadActual(entity.getCantidadActual())
                .cantidadMinima(entity.getCantidadMinima())
                .ultimaActualizacion(entity.getUltimaActualizacion())
                .build();
    }
}
