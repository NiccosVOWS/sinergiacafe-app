package pe.com.sinergiacafe.inventario.repository;

import pe.com.sinergiacafe.inventario.entity.MovimientoInventarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventarioEntity, Long> {
}
