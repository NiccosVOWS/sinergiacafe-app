package pe.com.sinergiacafe.clientes.repository;

import pe.com.sinergiacafe.clientes.entity.ProgramaFidelizacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramaFidelizacionRepository extends JpaRepository<ProgramaFidelizacionEntity, Long> {

    List<ProgramaFidelizacionEntity> findByActivoTrue();

}