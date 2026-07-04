package pe.com.sinergiacafe.recursoshumanos.repository;

import pe.com.sinergiacafe.recursoshumanos.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<RolEntity, Long> {
}
