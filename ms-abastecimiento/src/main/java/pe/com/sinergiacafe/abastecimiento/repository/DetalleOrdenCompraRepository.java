package pe.com.sinergiacafe.abastecimiento.repository;

import pe.com.sinergiacafe.abastecimiento.entity.DetalleOrdenCompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleOrdenCompraRepository extends JpaRepository<DetalleOrdenCompraEntity, Long> {

    List<DetalleOrdenCompraEntity> findByOrdenCompra_IdOrdenCompra(Long idOrdenCompra);
}