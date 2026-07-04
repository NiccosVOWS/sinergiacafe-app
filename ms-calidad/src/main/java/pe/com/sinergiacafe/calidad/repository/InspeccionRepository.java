package pe.com.sinergiacafe.calidad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.sinergiacafe.calidad.entity.InspeccionEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InspeccionRepository extends JpaRepository<InspeccionEntity, Long> {

    List<InspeccionEntity> findByIdEmpleado(Long idEmpleado);

    List<InspeccionEntity> findByAreaInspeccionada(String areaInspeccionada);

    List<InspeccionEntity> findByFechaInspeccionBetween(LocalDateTime inicio, LocalDateTime fin);
}