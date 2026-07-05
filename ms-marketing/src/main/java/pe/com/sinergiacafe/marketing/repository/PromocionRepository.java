package pe.com.sinergiacafe.marketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.sinergiacafe.marketing.entity.PromocionEntity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PromocionRepository extends JpaRepository<PromocionEntity, Long> {

    List<PromocionEntity> findByCampanaMarketing_IdCampanaMarketing(Long idCampanaMarketing);
    List<PromocionEntity> findByActivoTrue();
    List<PromocionEntity> findByTipoDescuento(PromocionEntity.TipoDescuento tipoDescuento);
    List<PromocionEntity> findByFechaInicioBetween(LocalDate inicio, LocalDate fin);
}