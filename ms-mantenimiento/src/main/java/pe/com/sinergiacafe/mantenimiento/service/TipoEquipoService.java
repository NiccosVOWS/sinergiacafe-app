package pe.com.sinergiacafe.mantenimiento.service;

import pe.com.sinergiacafe.mantenimiento.dto.TipoEquipoRequestDTO;
import pe.com.sinergiacafe.mantenimiento.dto.TipoEquipoResponseDTO;
import pe.com.sinergiacafe.mantenimiento.entity.TipoEquipoEntity;
import pe.com.sinergiacafe.mantenimiento.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.mantenimiento.repository.TipoEquipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoEquipoService {

    private final TipoEquipoRepository repository;

    public List<TipoEquipoResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public TipoEquipoResponseDTO findById(Long id) {
        TipoEquipoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoEquipo no encontrado con id: " + id));
        return toResponse(entity);
    }

    public TipoEquipoResponseDTO create(TipoEquipoRequestDTO dto) {
        TipoEquipoEntity entity = new TipoEquipoEntity();
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        return toResponse(repository.save(entity));
    }

    public TipoEquipoResponseDTO update(Long id, TipoEquipoRequestDTO dto) {
        TipoEquipoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoEquipo no encontrado con id: " + id));
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("TipoEquipo no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }

    private TipoEquipoResponseDTO toResponse(TipoEquipoEntity entity) {
        return TipoEquipoResponseDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
