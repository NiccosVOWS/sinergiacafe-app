package pe.com.sinergiacafe.inventario.repository;

import pe.com.sinergiacafe.inventario.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {
}
