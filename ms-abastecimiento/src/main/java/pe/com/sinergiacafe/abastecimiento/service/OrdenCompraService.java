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
        entity.setIdProveedor(dto.getIdProveedor());
        entity.setIdEmpleado(dto.getIdEmpleado());
        entity.setFechaOrden(java.time.LocalDateTime.now());
        entity.setMontoTotal(dto.getMontoTotal());
        entity.setEstado(dto.getEstado());
        return toResponse(repository.save(entity));
    }

    public OrdenCompraResponseDTO update(Long id, OrdenCompraRequestDTO dto) {
        OrdenCompraEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrdenCompra no encontrado con id: " + id));
        entity.setIdProveedor(dto.getIdProveedor());
        entity.setIdEmpleado(dto.getIdEmpleado());
        entity.setMontoTotal(dto.getMontoTotal());
        entity.setEstado(dto.getEstado());
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
                .idProveedor(entity.getIdProveedor())
                .idEmpleado(entity.getIdEmpleado())
                .fechaOrden(entity.getFechaOrden())
                .montoTotal(entity.getMontoTotal())
                .estado(entity.getEstado())
                .build();
    }
}
