package pe.com.sinergiacafe.mantenimiento.service;

import pe.com.sinergiacafe.mantenimiento.dto.MantenimientoRequestDTO;
import pe.com.sinergiacafe.mantenimiento.dto.MantenimientoResponseDTO;
import pe.com.sinergiacafe.mantenimiento.entity.MantenimientoEntity;
import pe.com.sinergiacafe.mantenimiento.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.mantenimiento.repository.MantenimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MantenimientoService {

    private final MantenimientoRepository repository;

    public List<MantenimientoResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public MantenimientoResponseDTO findById(Long id) {
        MantenimientoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mantenimiento no encontrado con id: " + id));
        return toResponse(entity);
    }

    public MantenimientoResponseDTO create(MantenimientoRequestDTO dto) {
        MantenimientoEntity entity = new MantenimientoEntity();
        entity.setEquipoId(dto.getEquipoId());
        entity.setTipoMantenimientoId(dto.getTipoMantenimientoId());
        entity.setFecha(dto.getFecha());
        entity.setResponsable(dto.getResponsable());
        entity.setCosto(dto.getCosto());
        entity.setObservaciones(dto.getObservaciones());
        return toResponse(repository.save(entity));
    }

    public MantenimientoResponseDTO update(Long id, MantenimientoRequestDTO dto) {
        MantenimientoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mantenimiento no encontrado con id: " + id));
        entity.setEquipoId(dto.getEquipoId());
        entity.setTipoMantenimientoId(dto.getTipoMantenimientoId());
        entity.setFecha(dto.getFecha());
        entity.setResponsable(dto.getResponsable());
        entity.setCosto(dto.getCosto());
        entity.setObservaciones(dto.getObservaciones());
        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Mantenimiento no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }

    private MantenimientoResponseDTO toResponse(MantenimientoEntity entity) {
        return MantenimientoResponseDTO.builder()
                .id(entity.getId())
                .equipoId(entity.getEquipoId())
                .tipoMantenimientoId(entity.getTipoMantenimientoId())
                .fecha(entity.getFecha())
                .responsable(entity.getResponsable())
                .costo(entity.getCosto())
                .observaciones(entity.getObservaciones())
                .build();
    }
}
