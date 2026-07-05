package pe.com.sinergiacafe.recursoshumanos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.recursoshumanos.dto.PermisoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.PermisoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.entity.PermisoEntity;
import pe.com.sinergiacafe.recursoshumanos.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.recursoshumanos.repository.PermisoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermisoServiceImpl implements PermisoService {

    private final PermisoRepository permisoRepository;

    @Override
    public List<PermisoResponseDTO> listarTodos() {
        return permisoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public List<PermisoResponseDTO> listarPorModulo(String modulo) {
        return permisoRepository.findByModulo(modulo)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public PermisoResponseDTO obtenerPorId(Long id) {
        PermisoEntity permiso = permisoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permiso", id));
        return toResponseDTO(permiso);
    }

    @Override
    public PermisoResponseDTO crear(PermisoRequestDTO request) {
        PermisoEntity permiso = PermisoEntity.builder()
                .modulo(request.getModulo())
                .accion(request.getAccion())
                .descripcion(request.getDescripcion())
                .build();
        return toResponseDTO(permisoRepository.save(permiso));
    }

    @Override
    public PermisoResponseDTO actualizar(Long id, PermisoRequestDTO request) {
        PermisoEntity permiso = permisoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permiso", id));
        permiso.setModulo(request.getModulo());
        permiso.setAccion(request.getAccion());
        permiso.setDescripcion(request.getDescripcion());
        return toResponseDTO(permisoRepository.save(permiso));
    }

    @Override
    public void eliminar(Long id) {
        if (!permisoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Permiso", id);
        }
        permisoRepository.deleteById(id);
    }

    public PermisoResponseDTO toResponseDTO(PermisoEntity entity) {
        return PermisoResponseDTO.builder()
                .idPermiso(entity.getIdPermiso())
                .modulo(entity.getModulo())
                .accion(entity.getAccion())
                .descripcion(entity.getDescripcion())
                .build();
    }
}