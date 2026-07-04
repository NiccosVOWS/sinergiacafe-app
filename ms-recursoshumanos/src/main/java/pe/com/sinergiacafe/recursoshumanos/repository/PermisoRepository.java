package pe.com.sinergiacafe.recursoshumanos.repository;

import pe.com.sinergiacafe.recursoshumanos.entity.PermisoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisoRepository extends JpaRepository<PermisoEntity, Long> {
}
