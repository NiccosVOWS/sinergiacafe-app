package pe.com.sinergiacafe.marketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.sinergiacafe.marketing.entity.PromocionEntity;
import pe.com.sinergiacafe.marketing.entity.TipoDescuento;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PromocionRepository extends JpaRepository<PromocionEntity, Long> {

    List<PromocionEntity> findByCampanaMarketingIdCampanaMarketing(Long idCampanaMarketing);

    List<PromocionEntity> findByActivo(Boolean activo);

    List<PromocionEntity> findByTipoDescuento(TipoDescuento tipoDescuento);

    List<PromocionEntity> findByFechaInicioBetween(LocalDate inicio, LocalDate fin);
}