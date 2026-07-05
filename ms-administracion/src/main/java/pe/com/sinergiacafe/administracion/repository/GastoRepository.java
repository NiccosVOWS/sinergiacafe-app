package pe.com.sinergiacafe.administracion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.sinergiacafe.administracion.entity.GastoEntity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GastoRepository extends JpaRepository<GastoEntity, Long> {

    List<GastoEntity> findByFechaGastoBetween(LocalDate inicio, LocalDate fin);
    List<GastoEntity> findByCategoriaGasto_IdCategoriaGasto(Long idCategoriaGasto);
}