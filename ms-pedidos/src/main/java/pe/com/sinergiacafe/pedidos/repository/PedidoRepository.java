package pe.com.sinergiacafe.pedidos.repository;

import pe.com.sinergiacafe.pedidos.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {

    List<PedidoEntity> findByIdCliente(Long idCliente);

    List<PedidoEntity> findByEstadoPedidoIdEstadoPedido(Long idEstadoPedido);
}