package pe.com.sinergiacafe.abastecimiento.service;

import pe.com.sinergiacafe.abastecimiento.dto.ContratoProveedorRequestDTO;
import pe.com.sinergiacafe.abastecimiento.dto.ContratoProveedorResponseDTO;
import pe.com.sinergiacafe.abastecimiento.entity.ContratoProveedorEntity;
import pe.com.sinergiacafe.abastecimiento.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.abastecimiento.repository.ContratoProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratoProveedorService {

    private final ContratoProveedorRepository repository;

    public List<ContratoProveedorResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public ContratoProveedorResponseDTO findById(Long id) {
        ContratoProveedorEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ContratoProveedor no encontrado con id: " + id));
        return toResponse(entity);
    }

    public ContratoProveedorResponseDTO create(ContratoProveedorRequestDTO dto) {
        ContratoProveedorEntity entity = new ContratoProveedorEntity();
        entity.setProveedorId(dto.getProveedorId());
        entity.setFechaInicio(dto.getFechaInicio());
        entity.setFechaFin(dto.getFechaFin());
        entity.setCondiciones(dto.getCondiciones());
        entity.setProductosPactados(dto.getProductosPactados());
        return toResponse(repository.save(entity));
    }

    public ContratoProveedorResponseDTO update(Long id, ContratoProveedorRequestDTO dto) {
        ContratoProveedorEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ContratoProveedor no encontrado con id: " + id));
        entity.setProveedorId(dto.getProveedorId());
        entity.setFechaInicio(dto.getFechaInicio());
        entity.setFechaFin(dto.getFechaFin());
        entity.setCondiciones(dto.getCondiciones());
        entity.setProductosPactados(dto.getProductosPactados());
        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("ContratoProveedor no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }

    private ContratoProveedorResponseDTO toResponse(ContratoProveedorEntity entity) {
        return ContratoProveedorResponseDTO.builder()
                .id(entity.getId())
                .proveedorId(entity.getProveedorId())
                .fechaInicio(entity.getFechaInicio())
                .fechaFin(entity.getFechaFin())
                .condiciones(entity.getCondiciones())
                .productosPactados(entity.getProductosPactados())
                .build();
    }
}
