package pe.com.sinergiacafe.calidad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.sinergiacafe.calidad.entity.TipoIncidenciaEntity;

@Repository
public interface TipoIncidenciaRepository extends JpaRepository<TipoIncidenciaEntity, Long> {
}