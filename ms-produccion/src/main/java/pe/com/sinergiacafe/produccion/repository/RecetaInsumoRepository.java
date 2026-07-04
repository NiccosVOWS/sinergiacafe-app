package pe.com.sinergiacafe.produccion.repository;

import pe.com.sinergiacafe.produccion.entity.RecetaInsumoEntity;
import pe.com.sinergiacafe.produccion.entity.RecetaInsumoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetaInsumoRepository extends JpaRepository<RecetaInsumoEntity, RecetaInsumoId> {

    List<RecetaInsumoEntity> findByRecetaIdReceta(Long idReceta);
}