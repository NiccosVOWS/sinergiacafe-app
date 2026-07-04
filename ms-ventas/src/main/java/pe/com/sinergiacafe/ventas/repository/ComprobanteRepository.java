package pe.com.sinergiacafe.ventas.repository;

import pe.com.sinergiacafe.ventas.entity.ComprobanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComprobanteRepository extends JpaRepository<ComprobanteEntity, Long> {

    List<ComprobanteEntity> findByIdPedido(Long idPedido);
    Optional<ComprobanteEntity> findBySerieAndNumero(String serie, String numero);
}