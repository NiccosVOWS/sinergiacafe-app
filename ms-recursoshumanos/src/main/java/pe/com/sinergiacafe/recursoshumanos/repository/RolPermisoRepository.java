package pe.com.sinergiacafe.recursoshumanos.repository;

import pe.com.sinergiacafe.recursoshumanos.entity.RolPermisoEntity;
import pe.com.sinergiacafe.recursoshumanos.entity.RolPermisoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolPermisoRepository extends JpaRepository<RolPermisoEntity, RolPermisoId> {

    List<RolPermisoEntity> findByRol_IdRol(Long idRol);
    List<RolPermisoEntity> findByPermiso_IdPermiso(Long idPermiso);
}