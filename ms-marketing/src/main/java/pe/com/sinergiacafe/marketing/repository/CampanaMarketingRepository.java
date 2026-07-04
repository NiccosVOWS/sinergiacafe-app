package pe.com.sinergiacafe.marketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.sinergiacafe.marketing.entity.CampanaMarketingEntity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CampanaMarketingRepository extends JpaRepository<CampanaMarketingEntity, Long> {

    List<CampanaMarketingEntity> findByNombreContainingIgnoreCase(String nombre);

    List<CampanaMarketingEntity> findByFechaInicioBetween(LocalDate inicio, LocalDate fin);

    List<CampanaMarketingEntity> findByCanal(String canal);
}