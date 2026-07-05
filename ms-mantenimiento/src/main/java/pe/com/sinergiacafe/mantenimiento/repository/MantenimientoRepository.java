package pe.com.sinergiacafe.mantenimiento.repository;

import pe.com.sinergiacafe.mantenimiento.entity.MantenimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MantenimientoRepository extends JpaRepository<MantenimientoEntity, Long> {

    List<MantenimientoEntity> findByEquipo_IdEquipo(Long idEquipo);
    List<MantenimientoEntity> findByTipoMantenimiento_IdTipoMantenimiento(Long idTipoMantenimiento);
    List<MantenimientoEntity> findByIdEmpleado(Long idEmpleado);
}