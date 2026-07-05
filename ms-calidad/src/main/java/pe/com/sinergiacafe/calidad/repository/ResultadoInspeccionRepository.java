package pe.com.sinergiacafe.calidad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.sinergiacafe.calidad.entity.ResultadoInspeccionEntity;

import java.util.List;

@Repository
public interface ResultadoInspeccionRepository extends JpaRepository<ResultadoInspeccionEntity, Long> {

    List<ResultadoInspeccionEntity> findByInspeccion_IdInspeccion(Long idInspeccion);
    List<ResultadoInspeccionEntity> findByEstandarCalidad_IdEstandarCalidad(Long idEstandarCalidad);
}