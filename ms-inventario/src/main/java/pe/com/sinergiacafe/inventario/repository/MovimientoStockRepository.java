package pe.com.sinergiacafe.inventario.repository;

import pe.com.sinergiacafe.inventario.entity.MovimientoStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoStockRepository extends JpaRepository<MovimientoStockEntity, Long> {
}
