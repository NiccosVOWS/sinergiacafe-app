package pe.com.sinergiacafe.abastecimiento.repository;

import pe.com.sinergiacafe.abastecimiento.entity.OrdenCompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenCompraRepository extends JpaRepository<OrdenCompraEntity, Long> {

    List<OrdenCompraEntity> findByProveedor_IdProveedor(Long idProveedor);
    List<OrdenCompraEntity> findByEstado(OrdenCompraEntity.EstadoOrden estado);
}