package pe.com.sinergiacafe.inventario.service;

import pe.com.sinergiacafe.inventario.dto.MovimientoStockRequestDTO;
import pe.com.sinergiacafe.inventario.dto.MovimientoStockResponseDTO;
import pe.com.sinergiacafe.inventario.entity.MovimientoStockEntity;
import pe.com.sinergiacafe.inventario.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.inventario.repository.MovimientoStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimientoStockService {

    private final MovimientoStockRepository repository;

    public List<MovimientoStockResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public MovimientoStockResponseDTO findById(Long id) {
        MovimientoStockEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MovimientoStock no encontrado con id: " + id));
        return toResponse(entity);
    }

    public MovimientoStockResponseDTO create(MovimientoStockRequestDTO dto) {
        MovimientoStockEntity entity = new MovimientoStockEntity();
        entity.setStockId(dto.getStockId());
        entity.setTipoMovimiento(dto.getTipoMovimiento());
        entity.setCantidad(dto.getCantidad());
        entity.setMotivo(dto.getMotivo());
        entity.setFecha(dto.getFecha());
        return toResponse(repository.save(entity));
    }

    public MovimientoStockResponseDTO update(Long id, MovimientoStockRequestDTO dto) {
        MovimientoStockEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MovimientoStock no encontrado con id: " + id));
        entity.setStockId(dto.getStockId());
        entity.setTipoMovimiento(dto.getTipoMovimiento());
        entity.setCantidad(dto.getCantidad());
        entity.setMotivo(dto.getMotivo());
        entity.setFecha(dto.getFecha());
        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("MovimientoStock no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }

    private MovimientoStockResponseDTO toResponse(MovimientoStockEntity entity) {
        return MovimientoStockResponseDTO.builder()
                .id(entity.getId())
                .stockId(entity.getStockId())
                .tipoMovimiento(entity.getTipoMovimiento())
                .cantidad(entity.getCantidad())
                .motivo(entity.getMotivo())
                .fecha(entity.getFecha())
                .build();
    }
}
