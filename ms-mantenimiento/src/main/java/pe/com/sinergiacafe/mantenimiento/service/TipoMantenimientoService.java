package pe.com.sinergiacafe.mantenimiento.service;

import pe.com.sinergiacafe.mantenimiento.dto.TipoMantenimientoRequestDTO;
import pe.com.sinergiacafe.mantenimiento.dto.TipoMantenimientoResponseDTO;
import pe.com.sinergiacafe.mantenimiento.entity.TipoMantenimientoEntity;
import pe.com.sinergiacafe.mantenimiento.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.mantenimiento.repository.TipoMantenimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoMantenimientoService {

    private final TipoMantenimientoRepository repository;

    public List<TipoMantenimientoResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public TipoMantenimientoResponseDTO findById(Long id) {
        TipoMantenimientoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoMantenimiento no encontrado con id: " + id));
        return toResponse(entity);
    }

    public TipoMantenimientoResponseDTO create(TipoMantenimientoRequestDTO dto) {
        TipoMantenimientoEntity entity = new TipoMantenimientoEntity();
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        return toResponse(repository.save(entity));
    }

    public TipoMantenimientoResponseDTO update(Long id, TipoMantenimientoRequestDTO dto) {
        TipoMantenimientoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoMantenimiento no encontrado con id: " + id));
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("TipoMantenimiento no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }

    private TipoMantenimientoResponseDTO toResponse(TipoMantenimientoEntity entity) {
        return TipoMantenimientoResponseDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
