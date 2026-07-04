package pe.com.sinergiacafe.ventas.repository;

import pe.com.sinergiacafe.ventas.entity.MetodoPagoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPagoEntity, Long> {

    List<MetodoPagoEntity> findByActivoTrue();
}