package pe.com.sinergiacafe.calidad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.sinergiacafe.calidad.entity.EstadoIncidencia;
import pe.com.sinergiacafe.calidad.entity.IncidenciaEntity;

import java.util.List;

@Repository
public interface IncidenciaRepository extends JpaRepository<IncidenciaEntity, Long> {

    List<IncidenciaEntity> findByIdEmpleado(Long idEmpleado);

    List<IncidenciaEntity> findByEstado(EstadoIncidencia estado);

    List<IncidenciaEntity> findByTipoIncidenciaIdTipoIncidencia(Long idTipoIncidencia);
}