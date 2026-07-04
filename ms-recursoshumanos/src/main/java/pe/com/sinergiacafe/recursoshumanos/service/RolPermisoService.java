package pe.com.sinergiacafe.recursoshumanos.service;

import pe.com.sinergiacafe.recursoshumanos.dto.RolPermisoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.entity.RolPermisoEntity;
import pe.com.sinergiacafe.recursoshumanos.repository.RolPermisoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolPermisoService {

    private final RolPermisoRepository repository;

    public List<RolPermisoEntity> findAll() {
        return repository.findAll();
    }

    public RolPermisoEntity create(RolPermisoRequestDTO dto) {
        RolPermisoEntity entity = new RolPermisoEntity();
        entity.setIdRol(dto.getIdRol());
        entity.setIdPermiso(dto.getIdPermiso());
        return repository.save(entity);
    }

    public void delete(Long idRol, Long idPermiso) {
        repository.deleteById(new pe.com.sinergiacafe.recursoshumanos.entity.RolPermisoId(idRol, idPermiso));
    }
}
