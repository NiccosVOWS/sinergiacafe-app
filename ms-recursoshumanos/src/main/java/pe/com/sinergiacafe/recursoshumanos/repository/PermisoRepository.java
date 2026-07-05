package pe.com.sinergiacafe.recursoshumanos.repository;

import pe.com.sinergiacafe.recursoshumanos.entity.PermisoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermisoRepository extends JpaRepository<PermisoEntity, Long> {

    List<PermisoEntity> findByModulo(String modulo);
    List<PermisoEntity> findByModuloAndAccion(String modulo, String accion);
}