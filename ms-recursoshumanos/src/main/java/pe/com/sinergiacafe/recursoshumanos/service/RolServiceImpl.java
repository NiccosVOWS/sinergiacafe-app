package pe.com.sinergiacafe.recursoshumanos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.recursoshumanos.dto.RolRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.RolResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.entity.RolEntity;
import pe.com.sinergiacafe.recursoshumanos.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.recursoshumanos.repository.RolRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    @Override
    public List<RolResponseDTO> listarTodos() {
        return rolRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public List<RolResponseDTO> listarActivos() {
        return rolRepository.findByActivoTrue()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public RolResponseDTO obtenerPorId(Long id) {
        RolEntity rol = rolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol", id));
        return toResponseDTO(rol);
    }

    @Override
    public RolResponseDTO crear(RolRequestDTO request) {
        RolEntity rol = RolEntity.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .build();
        return toResponseDTO(rolRepository.save(rol));
    }

    @Override
    public RolResponseDTO actualizar(Long id, RolRequestDTO request) {
        RolEntity rol = rolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol", id));
        rol.setNombre(request.getNombre());
        rol.setDescripcion(request.getDescripcion());
        return toResponseDTO(rolRepository.save(rol));
    }

    @Override
    public RolResponseDTO actualizarEstado(Long id, Boolean activo) {
        RolEntity rol = rolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol", id));
        rol.setActivo(activo);
        return toResponseDTO(rolRepository.save(rol));
    }

    @Override
    public void eliminar(Long id) {
        if (!rolRepository.existsById(id)) {
            throw new ResourceNotFoundException("Rol", id);
        }
        rolRepository.deleteById(id);
    }

    public RolResponseDTO toResponseDTO(RolEntity entity) {
        return RolResponseDTO.builder()
                .idRol(entity.getIdRol())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .activo(entity.getActivo())
                .build();
    }
}