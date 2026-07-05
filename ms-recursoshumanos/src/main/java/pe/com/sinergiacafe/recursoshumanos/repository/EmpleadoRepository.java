package pe.com.sinergiacafe.recursoshumanos.repository;

import pe.com.sinergiacafe.recursoshumanos.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Long> {

    Optional<EmpleadoEntity> findByDni(String dni);
    boolean existsByDni(String dni);
    List<EmpleadoEntity> findByEstadoLaboral(EmpleadoEntity.EstadoLaboral estadoLaboral);
    List<EmpleadoEntity> findByCargo_IdCargo(Long idCargo);
    List<EmpleadoEntity> findByTurno_IdTurno(Long idTurno);
}