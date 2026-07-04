package pe.com.sinergiacafe.finanzas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.sinergiacafe.finanzas.entity.ReporteFinancieroEntity;

import java.util.Optional;

@Repository
public interface ReporteFinancieroRepository extends JpaRepository<ReporteFinancieroEntity, Long> {

    Optional<ReporteFinancieroEntity> findByPeriodo(String periodo);

}