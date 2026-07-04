package pe.com.sinergiacafe.mantenimiento.repository;

import pe.com.sinergiacafe.mantenimiento.entity.TipoMantenimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoMantenimientoRepository extends JpaRepository<TipoMantenimientoEntity, Long> {
}
