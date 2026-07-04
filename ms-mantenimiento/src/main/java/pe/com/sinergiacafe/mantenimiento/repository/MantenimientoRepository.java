package pe.com.sinergiacafe.mantenimiento.repository;

import pe.com.sinergiacafe.mantenimiento.entity.MantenimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MantenimientoRepository extends JpaRepository<MantenimientoEntity, Long> {
}
