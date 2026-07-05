package pe.com.sinergiacafe.recursoshumanos.repository;

import pe.com.sinergiacafe.recursoshumanos.entity.AsistenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AsistenciaRepository extends JpaRepository<AsistenciaEntity, Long> {

    List<AsistenciaEntity> findByEmpleado_IdEmpleado(Long idEmpleado);
    Optional<AsistenciaEntity> findByEmpleado_IdEmpleadoAndFecha(Long idEmpleado, LocalDate fecha);
    List<AsistenciaEntity> findByFecha(LocalDate fecha);
}