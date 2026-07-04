package pe.com.sinergiacafe.ventas.repository;

import pe.com.sinergiacafe.ventas.entity.CierreCajaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CierreCajaRepository extends JpaRepository<CierreCajaEntity, Long> {

    List<CierreCajaEntity> findByCajaIdCaja(Long idCaja);
}