package pe.com.sinergiacafe.finanzas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.sinergiacafe.finanzas.entity.PresupuestoEntity;

import java.util.List;

@Repository
public interface PresupuestoRepository extends JpaRepository<PresupuestoEntity, Long> {

    List<PresupuestoEntity> findByPeriodo(String periodo);

    List<PresupuestoEntity> findByArea(String area);

}