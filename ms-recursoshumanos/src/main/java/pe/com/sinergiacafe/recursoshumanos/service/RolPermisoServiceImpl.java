package pe.com.sinergiacafe.recursoshumanos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.recursoshumanos.dto.RolPermisoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.RolPermisoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.RolResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.entity.PermisoEntity;
import pe.com.sinergiacafe.recursoshumanos.entity.RolEntity;
import pe.com.sinergiacafe.recursoshumanos.entity.RolPermisoEntity;
import pe.com.sinergiacafe.recursoshumanos.entity.RolPermisoId;
import pe.com.sinergiacafe.recursoshumanos.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.recursoshumanos.repository.PermisoRepository;
import pe.com.sinergiacafe.recursoshumanos.repository.RolPermisoRepository;
import pe.com.sinergiacafe.recursoshumanos.repository.RolRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolPermisoServiceImpl implements RolPermisoService {

    private final RolPermisoRepository rolPermisoRepository;
    private final RolRepository rolRepository;
    private final PermisoRepository permisoRepository;
    private final PermisoServiceImpl permisoService;

    @Override
    public List<RolPermisoResponseDTO> listarTodos() {
        return rolPermisoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public List<RolPermisoResponseDTO> listarPorRol(Long idRol) {
        return rolPermisoRepository.findByRol_IdRol(idRol)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public RolPermisoResponseDTO asignar(RolPermisoRequestDTO request) {
        RolEntity rol = rolRepository.findById(request.getIdRol())
                .orElseThrow(() -> new ResourceNotFoundException("Rol", request.getIdRol()));
        PermisoEntity permiso = permisoRepository.findById(request.getIdPermiso())
                .orElseThrow(() -> new ResourceNotFoundException("Permiso", request.getIdPermiso()));

        RolPermisoId id = new RolPermisoId(request.getIdRol(), request.getIdPermiso());
        RolPermisoEntity entity = RolPermisoEntity.builder()
                .id(id)
                .rol(rol)
                .permiso(permiso)
                .build();

        return toResponseDTO(rolPermisoRepository.save(entity));
    }

    @Override
    public void eliminar(Long idRol, Long idPermiso) {
        RolPermisoId id = new RolPermisoId(idRol, idPermiso);
        if (!rolPermisoRepository.existsById(id)) {
            throw new ResourceNotFoundException("RolPermiso no encontrado");
        }
        rolPermisoRepository.deleteById(id);
    }

    private RolPermisoResponseDTO toResponseDTO(RolPermisoEntity entity) {
        return RolPermisoResponseDTO.builder()
                .rol(RolResponseDTO.builder()
                        .idRol(entity.getRol().getIdRol())
                        .nombre(entity.getRol().getNombre())
                        .descripcion(entity.getRol().getDescripcion())
                        .activo(entity.getRol().getActivo())
                        .build())
                .permiso(permisoService.toResponseDTO(entity.getPermiso()))
                .build();
    }
}