package pe.com.sinergiacafe.recursoshumanos.service;

import pe.com.sinergiacafe.recursoshumanos.dto.PermisoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.PermisoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.entity.PermisoEntity;
import pe.com.sinergiacafe.recursoshumanos.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.recursoshumanos.repository.PermisoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermisoService {

    private final PermisoRepository repository;

    public List<PermisoResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public PermisoResponseDTO findById(Long id) {
        PermisoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permiso no encontrado con id: " + id));
        return toResponse(entity);
    }

    public PermisoResponseDTO create(PermisoRequestDTO dto) {
        PermisoEntity entity = new PermisoEntity();
        entity.setModulo(dto.getModulo());
        entity.setAccion(dto.getAccion());
        entity.setDescripcion(dto.getDescripcion());
        return toResponse(repository.save(entity));
    }

    public PermisoResponseDTO update(Long id, PermisoRequestDTO dto) {
        PermisoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permiso no encontrado con id: " + id));
        entity.setModulo(dto.getModulo());
        entity.setAccion(dto.getAccion());
        entity.setDescripcion(dto.getDescripcion());
        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Permiso no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }

    private PermisoResponseDTO toResponse(PermisoEntity entity) {
        return PermisoResponseDTO.builder()
                .id(entity.getId())
                .modulo(entity.getModulo())
                .accion(entity.getAccion())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
