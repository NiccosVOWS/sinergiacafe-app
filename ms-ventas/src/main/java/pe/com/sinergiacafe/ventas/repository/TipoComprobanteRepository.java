package pe.com.sinergiacafe.ventas.repository;

import pe.com.sinergiacafe.ventas.entity.TipoComprobanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoComprobanteRepository extends JpaRepository<TipoComprobanteEntity, Long> {

}