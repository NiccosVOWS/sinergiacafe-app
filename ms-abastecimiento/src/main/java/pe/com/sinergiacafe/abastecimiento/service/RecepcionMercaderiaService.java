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
        entity.setIdOrdenCompra(dto.getIdOrdenCompra());
        entity.setIdEmpleado(dto.getIdEmpleado());
        entity.setFechaRecepcion(java.time.LocalDateTime.now());
        entity.setConforme(dto.getConforme());
        entity.setObservaciones(dto.getObservaciones());
        return toResponse(repository.save(entity));
    }

    public RecepcionMercaderiaResponseDTO update(Long id, RecepcionMercaderiaRequestDTO dto) {
        RecepcionMercaderiaEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RecepcionMercaderia no encontrado con id: " + id));
        entity.setIdOrdenCompra(dto.getIdOrdenCompra());
        entity.setIdEmpleado(dto.getIdEmpleado());
        entity.setConforme(dto.getConforme());
        entity.setObservaciones(dto.getObservaciones());
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
                .idOrdenCompra(entity.getIdOrdenCompra())
                .idEmpleado(entity.getIdEmpleado())
                .fechaRecepcion(entity.getFechaRecepcion())
                .conforme(entity.getConforme())
                .observaciones(entity.getObservaciones())
                .build();
    }
}
