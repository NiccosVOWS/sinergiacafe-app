package pe.com.sinergiacafe.inventario.repository;

import pe.com.sinergiacafe.inventario.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {

    List<ProductoEntity> findByDisponibleTrue();
    List<ProductoEntity> findByCategoriaProducto_IdCategoriaProducto(Long idCategoriaProducto);
}