package pe.com.sinergiacafe.calidad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.sinergiacafe.calidad.entity.EstandarCalidadEntity;

import java.util.List;

@Repository
public interface EstandarCalidadRepository extends JpaRepository<EstandarCalidadEntity, Long> {

    List<EstandarCalidadEntity> findByAplicableA(String aplicableA);
}