package pe.com.sinergiacafe.ventas.repository;

import pe.com.sinergiacafe.ventas.entity.CajaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CajaRepository extends JpaRepository<CajaEntity, Long> {

    Optional<CajaEntity> findFirstByEstadoOrderByFechaAperturaDesc(CajaEntity.EstadoCaja estado);
    List<CajaEntity> findByIdEmpleado(Long idEmpleado);
}