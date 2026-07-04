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
        entity.setProductoId(dto.getProductoId());
        entity.setCantidadActual(dto.getCantidadActual());
        entity.setStockMinimo(dto.getStockMinimo());
        entity.setFechaActualizacion(dto.getFechaActualizacion());
        return toResponse(repository.save(entity));
    }

    public StockResponseDTO update(Long id, StockRequestDTO dto) {
        StockEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock no encontrado con id: " + id));
        entity.setProductoId(dto.getProductoId());
        entity.setCantidadActual(dto.getCantidadActual());
        entity.setStockMinimo(dto.getStockMinimo());
        entity.setFechaActualizacion(dto.getFechaActualizacion());
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
                .productoId(entity.getProductoId())
                .cantidadActual(entity.getCantidadActual())
                .stockMinimo(entity.getStockMinimo())
                .fechaActualizacion(entity.getFechaActualizacion())
                .build();
    }
}
