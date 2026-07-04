package pe.com.sinergiacafe.abastecimiento.service;

import pe.com.sinergiacafe.abastecimiento.dto.OrdenCompraRequestDTO;
import pe.com.sinergiacafe.abastecimiento.dto.OrdenCompraResponseDTO;
import pe.com.sinergiacafe.abastecimiento.entity.OrdenCompraEntity;
import pe.com.sinergiacafe.abastecimiento.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.abastecimiento.repository.OrdenCompraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdenCompraService {

    private final OrdenCompraRepository repository;

    public List<OrdenCompraResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public OrdenCompraResponseDTO findById(Long id) {
        OrdenCompraEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrdenCompra no encontrado con id: " + id));
        return toResponse(entity);
    }

    public OrdenCompraResponseDTO create(OrdenCompraRequestDTO dto) {
        OrdenCompraEntity entity = new OrdenCompraEntity();
        entity.setProveedorId(dto.getProveedorId());
        entity.setFecha(dto.getFecha());
        entity.setEstado(dto.getEstado());
        entity.setMontoTotal(dto.getMontoTotal());
        return toResponse(repository.save(entity));
    }

    public OrdenCompraResponseDTO update(Long id, OrdenCompraRequestDTO dto) {
        OrdenCompraEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrdenCompra no encontrado con id: " + id));
        entity.setProveedorId(dto.getProveedorId());
        entity.setFecha(dto.getFecha());
        entity.setEstado(dto.getEstado());
        entity.setMontoTotal(dto.getMontoTotal());
        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("OrdenCompra no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }

    private OrdenCompraResponseDTO toResponse(OrdenCompraEntity entity) {
        return OrdenCompraResponseDTO.builder()
                .id(entity.getId())
                .proveedorId(entity.getProveedorId())
                .fecha(entity.getFecha())
                .estado(entity.getEstado())
                .montoTotal(entity.getMontoTotal())
                .build();
    }
}
