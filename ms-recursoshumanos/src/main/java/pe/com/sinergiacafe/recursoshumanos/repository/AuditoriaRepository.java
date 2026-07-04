package pe.com.sinergiacafe.recursoshumanos.repository;

import pe.com.sinergiacafe.recursoshumanos.entity.AuditoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriaRepository extends JpaRepository<AuditoriaEntity, Long> {
}
