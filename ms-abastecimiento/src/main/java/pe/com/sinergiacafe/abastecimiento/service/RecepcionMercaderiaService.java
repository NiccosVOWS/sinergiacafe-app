package pe.com.sinergiacafe.abastecimiento.service;

import pe.com.sinergiacafe.abastecimiento.dto.RecepcionMercaderiaRequestDTO;
import pe.com.sinergiacafe.abastecimiento.dto.RecepcionMercaderiaResponseDTO;
import pe.com.sinergiacafe.abastecimiento.entity.RecepcionMercaderiaEntity;
import pe.com.sinergiacafe.abastecimiento.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.abastecimiento.repository.RecepcionMercaderiaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecepcionMercaderiaService {

    private final RecepcionMercaderiaRepository repository;

    public List<RecepcionMercaderiaResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public RecepcionMercaderiaResponseDTO findById(Long id) {
        RecepcionMercaderiaEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RecepcionMercaderia no encontrado con id: " + id));
        return toResponse(entity);
    }

    public RecepcionMercaderiaResponseDTO create(RecepcionMercaderiaRequestDTO dto) {
        RecepcionMercaderiaEntity entity = new RecepcionMercaderiaEntity();
        entity.setOrdenCompraId(dto.getOrdenCompraId());
        entity.setProveedorId(dto.getProveedorId());
        entity.setFecha(dto.getFecha());
        entity.setCantidadRecibida(dto.getCantidadRecibida());
        entity.setConforme(dto.getConforme());
        return toResponse(repository.save(entity));
    }

    public RecepcionMercaderiaResponseDTO update(Long id, RecepcionMercaderiaRequestDTO dto) {
        RecepcionMercaderiaEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RecepcionMercaderia no encontrado con id: " + id));
        entity.setOrdenCompraId(dto.getOrdenCompraId());
        entity.setProveedorId(dto.getProveedorId());
        entity.setFecha(dto.getFecha());
        entity.setCantidadRecibida(dto.getCantidadRecibida());
        entity.setConforme(dto.getConforme());
        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("RecepcionMercaderia no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }

    private RecepcionMercaderiaResponseDTO toResponse(RecepcionMercaderiaEntity entity) {
        return RecepcionMercaderiaResponseDTO.builder()
                .id(entity.getId())
                .ordenCompraId(entity.getOrdenCompraId())
                .proveedorId(entity.getProveedorId())
                .fecha(entity.getFecha())
                .cantidadRecibida(entity.getCantidadRecibida())
                .conforme(entity.getConforme())
                .build();
    }
}
