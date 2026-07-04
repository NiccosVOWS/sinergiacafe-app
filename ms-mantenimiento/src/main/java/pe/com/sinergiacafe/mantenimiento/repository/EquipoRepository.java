package pe.com.sinergiacafe.mantenimiento.repository;

import pe.com.sinergiacafe.mantenimiento.entity.EquipoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends JpaRepository<EquipoEntity, Long> {
}
