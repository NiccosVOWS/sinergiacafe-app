package pe.com.sinergiacafe.recursoshumanos.service;

import pe.com.sinergiacafe.recursoshumanos.dto.UsuarioRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.UsuarioResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.entity.UsuarioEntity;
import pe.com.sinergiacafe.recursoshumanos.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.recursoshumanos.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    public List<UsuarioResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public UsuarioResponseDTO findById(Long id) {
        UsuarioEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
        return toResponse(entity);
    }

    public UsuarioResponseDTO create(UsuarioRequestDTO dto) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setIdEmpleado(dto.getIdEmpleado());
        entity.setIdRol(dto.getIdRol());
        entity.setUsername(dto.getUsername());
        entity.setPasswordHash(dto.getPasswordHash());
        entity.setActivo(dto.getActivo());
        entity.setFechaCreacion(java.time.LocalDateTime.now());
        entity.setUltimoAcceso(dto.getUltimoAcceso());
        return toResponse(repository.save(entity));
    }

    public UsuarioResponseDTO update(Long id, UsuarioRequestDTO dto) {
        UsuarioEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
        entity.setIdEmpleado(dto.getIdEmpleado());
        entity.setIdRol(dto.getIdRol());
        entity.setUsername(dto.getUsername());
        entity.setPasswordHash(dto.getPasswordHash());
        entity.setActivo(dto.getActivo());
        entity.setUltimoAcceso(dto.getUltimoAcceso());
        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }

    private UsuarioResponseDTO toResponse(UsuarioEntity entity) {
        return UsuarioResponseDTO.builder()
                .id(entity.getId())
                .idEmpleado(entity.getIdEmpleado())
                .idRol(entity.getIdRol())
                .username(entity.getUsername())
                .passwordHash(entity.getPasswordHash())
                .activo(entity.getActivo())
                .fechaCreacion(entity.getFechaCreacion())
                .ultimoAcceso(entity.getUltimoAcceso())
                .build();
    }
}
