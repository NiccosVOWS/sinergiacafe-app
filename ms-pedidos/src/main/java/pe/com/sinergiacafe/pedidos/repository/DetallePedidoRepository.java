package pe.com.sinergiacafe.pedidos.repository;

import pe.com.sinergiacafe.pedidos.entity.DetallePedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedidoEntity, Long> {

    List<DetallePedidoEntity> findByPedidoIdPedido(Long idPedido);
}