package pe.com.sinergiacafe.inventario.repository;

import pe.com.sinergiacafe.inventario.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {

    Optional<StockEntity> findByIdInsumo(Long idInsumo);
}