package pe.com.sinergiacafe.recursoshumanos.repository;

import pe.com.sinergiacafe.recursoshumanos.entity.EvaluacionDesempenoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluacionDesempenoRepository extends JpaRepository<EvaluacionDesempenoEntity, Long> {
}
