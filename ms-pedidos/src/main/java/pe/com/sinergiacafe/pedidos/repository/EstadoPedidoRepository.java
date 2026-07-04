package pe.com.sinergiacafe.pedidos.repository;

import pe.com.sinergiacafe.pedidos.entity.EstadoPedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoPedidoRepository extends JpaRepository<EstadoPedidoEntity, Long> {

}