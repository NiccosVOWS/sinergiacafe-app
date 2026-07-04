package pe.com.sinergiacafe.inventario.repository;

import pe.com.sinergiacafe.inventario.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
}
