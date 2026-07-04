package pe.com.sinergiacafe.mantenimiento.repository;

import pe.com.sinergiacafe.mantenimiento.entity.TipoEquipoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoEquipoRepository extends JpaRepository<TipoEquipoEntity, Long> {
}
