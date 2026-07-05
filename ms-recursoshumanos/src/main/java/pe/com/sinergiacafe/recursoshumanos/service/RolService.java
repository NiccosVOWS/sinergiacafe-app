package pe.com.sinergiacafe.recursoshumanos.service;

import pe.com.sinergiacafe.recursoshumanos.dto.RolRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.RolResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.entity.RolEntity;
import pe.com.sinergiacafe.recursoshumanos.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.recursoshumanos.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolService {

    private final RolRepository repository;

    public List<RolResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public RolResponseDTO findById(Long id) {
        RolEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado con id: " + id));
        return toResponse(entity);
    }

    public RolResponseDTO create(RolRequestDTO dto) {
        RolEntity entity = new RolEntity();
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setActivo(dto.getActivo());
        return toResponse(repository.save(entity));
    }

    public RolResponseDTO update(Long id, RolRequestDTO dto) {
        RolEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado con id: " + id));
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setActivo(dto.getActivo());
        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Rol no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }

    private RolResponseDTO toResponse(RolEntity entity) {
        return RolResponseDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .activo(entity.getActivo())
                .build();
    }
}
