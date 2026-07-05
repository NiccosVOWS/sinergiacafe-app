package pe.com.sinergiacafe.inventario.repository;

import pe.com.sinergiacafe.inventario.entity.MovimientoStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientoStockRepository extends JpaRepository<MovimientoStockEntity, Long> {

    List<MovimientoStockEntity> findByStock_IdStock(Long idStock);
    List<MovimientoStockEntity> findByTipoMovimiento(MovimientoStockEntity.TipoMovimiento tipoMovimiento);
}