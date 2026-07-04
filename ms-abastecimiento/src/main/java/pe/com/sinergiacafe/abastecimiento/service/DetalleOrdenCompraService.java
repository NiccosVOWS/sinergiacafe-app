package pe.com.sinergiacafe.abastecimiento.service;

import pe.com.sinergiacafe.abastecimiento.dto.DetalleOrdenCompraRequestDTO;
import pe.com.sinergiacafe.abastecimiento.dto.DetalleOrdenCompraResponseDTO;
import pe.com.sinergiacafe.abastecimiento.entity.DetalleOrdenCompraEntity;
import pe.com.sinergiacafe.abastecimiento.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.abastecimiento.repository.DetalleOrdenCompraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetalleOrdenCompraService {

    private final DetalleOrdenCompraRepository repository;

    public List<DetalleOrdenCompraResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public DetalleOrdenCompraResponseDTO findById(Long id) {
        DetalleOrdenCompraEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DetalleOrdenCompra no encontrado con id: " + id));
        return toResponse(entity);
    }

    public DetalleOrdenCompraResponseDTO create(DetalleOrdenCompraRequestDTO dto) {
        DetalleOrdenCompraEntity entity = new DetalleOrdenCompraEntity();
        entity.setIdOrdenCompra(dto.getIdOrdenCompra());
        entity.setIdInsumo(dto.getIdInsumo());
        entity.setCantidad(dto.getCantidad());
        entity.setPrecioUnitario(dto.getPrecioUnitario());
        entity.setSubtotal(dto.getCantidad().multiply(dto.getPrecioUnitario()));
        return toResponse(repository.save(entity));
    }

    public DetalleOrdenCompraResponseDTO update(Long id, DetalleOrdenCompraRequestDTO dto) {
        DetalleOrdenCompraEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DetalleOrdenCompra no encontrado con id: " + id));
        entity.setIdOrdenCompra(dto.getIdOrdenCompra());
        entity.setIdInsumo(dto.getIdInsumo());
        entity.setCantidad(dto.getCantidad());
        entity.setPrecioUnitario(dto.getPrecioUnitario());
        entity.setSubtotal(dto.getCantidad().multiply(dto.getPrecioUnitario()));
        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("DetalleOrdenCompra no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }

    private DetalleOrdenCompraResponseDTO toResponse(DetalleOrdenCompraEntity entity) {
        return DetalleOrdenCompraResponseDTO.builder()
                .id(entity.getId())
                .idOrdenCompra(entity.getIdOrdenCompra())
                .idInsumo(entity.getIdInsumo())
                .cantidad(entity.getCantidad())
                .precioUnitario(entity.getPrecioUnitario())
                .subtotal(entity.getSubtotal())
                .build();
    }
}
