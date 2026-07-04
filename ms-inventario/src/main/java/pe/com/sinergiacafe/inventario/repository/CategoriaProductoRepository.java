package pe.com.sinergiacafe.inventario.repository;

import pe.com.sinergiacafe.inventario.entity.CategoriaProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaProductoRepository extends JpaRepository<CategoriaProductoEntity, Long> {
}
