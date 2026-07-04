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
        entity.setEmpleadoId(dto.getEmpleadoId());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setRolId(dto.getRolId());
        entity.setEstado(dto.getEstado());
        return toResponse(repository.save(entity));
    }

    public UsuarioResponseDTO update(Long id, UsuarioRequestDTO dto) {
        UsuarioEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
        entity.setEmpleadoId(dto.getEmpleadoId());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setRolId(dto.getRolId());
        entity.setEstado(dto.getEstado());
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
                .empleadoId(entity.getEmpleadoId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .rolId(entity.getRolId())
                .estado(entity.getEstado())
                .build();
    }
}
