package pe.com.sinergiacafe.abastecimiento.repository;

import pe.com.sinergiacafe.abastecimiento.entity.RecepcionMercaderiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecepcionMercaderiaRepository extends JpaRepository<RecepcionMercaderiaEntity, Long> {

    List<RecepcionMercaderiaEntity> findByOrdenCompra_IdOrdenCompra(Long idOrdenCompra);
    List<RecepcionMercaderiaEntity> findByConformeTrue();
    List<RecepcionMercaderiaEntity> findByConformeFalse();
}