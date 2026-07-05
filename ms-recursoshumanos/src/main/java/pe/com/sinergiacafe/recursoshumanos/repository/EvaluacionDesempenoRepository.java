package pe.com.sinergiacafe.recursoshumanos.repository;

import pe.com.sinergiacafe.recursoshumanos.entity.EvaluacionDesempenoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluacionDesempenoRepository extends JpaRepository<EvaluacionDesempenoEntity, Long> {

    List<EvaluacionDesempenoEntity> findByEmpleado_IdEmpleado(Long idEmpleado);
    List<EvaluacionDesempenoEntity> findByEmpleado_IdEmpleadoOrderByFechaEvaluacionDesc(Long idEmpleado);
}