package pe.com.sinergiacafe.finanzas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.sinergiacafe.finanzas.entity.CuentaPorPagarEntity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CuentaPorPagarRepository extends JpaRepository<CuentaPorPagarEntity, Long> {

    List<CuentaPorPagarEntity> findByIdProveedor(Long idProveedor);
    List<CuentaPorPagarEntity> findByEstado(CuentaPorPagarEntity.EstadoCuentaPorPagar estado);
    List<CuentaPorPagarEntity> findByFechaVencimientoBefore(LocalDate fecha);
}