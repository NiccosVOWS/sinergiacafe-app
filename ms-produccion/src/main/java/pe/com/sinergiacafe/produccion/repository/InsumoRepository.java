package pe.com.sinergiacafe.produccion.repository;

import pe.com.sinergiacafe.produccion.entity.InsumoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsumoRepository extends JpaRepository<InsumoEntity, Long> {

    List<InsumoEntity> findByCategoriaInsumo_idCategoriaInsumo(Long idCategoriaInsumo);
    List<InsumoEntity> findByActivoTrue();
}