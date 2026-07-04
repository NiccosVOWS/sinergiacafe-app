package pe.com.sinergiacafe.recursoshumanos.service;

import pe.com.sinergiacafe.recursoshumanos.dto.RolPermisoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.RolPermisoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.entity.RolPermisoEntity;
import pe.com.sinergiacafe.recursoshumanos.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.recursoshumanos.repository.RolPermisoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolPermisoService {

    private final RolPermisoRepository repository;

    public List<RolPermisoResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public RolPermisoResponseDTO findById(Long id) {
        RolPermisoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RolPermiso no encontrado con id: " + id));
        return toResponse(entity);
    }

    public RolPermisoResponseDTO create(RolPermisoRequestDTO dto) {
        RolPermisoEntity entity = new RolPermisoEntity();
        entity.setRolId(dto.getRolId());
        entity.setPermisoId(dto.getPermisoId());
        return toResponse(repository.save(entity));
    }

    public RolPermisoResponseDTO update(Long id, RolPermisoRequestDTO dto) {
        RolPermisoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RolPermiso no encontrado con id: " + id));
        entity.setRolId(dto.getRolId());
        entity.setPermisoId(dto.getPermisoId());
        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("RolPermiso no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }

    private RolPermisoResponseDTO toResponse(RolPermisoEntity entity) {
        return RolPermisoResponseDTO.builder()
                .id(entity.getId())
                .rolId(entity.getRolId())
                .permisoId(entity.getPermisoId())
                .build();
    }
}
