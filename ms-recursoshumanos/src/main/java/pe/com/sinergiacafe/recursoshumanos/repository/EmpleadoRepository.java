package pe.com.sinergiacafe.recursoshumanos.repository;

import pe.com.sinergiacafe.recursoshumanos.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Long> {
}
