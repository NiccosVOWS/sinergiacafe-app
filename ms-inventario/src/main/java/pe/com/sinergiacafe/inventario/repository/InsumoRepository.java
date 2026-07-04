package pe.com.sinergiacafe.inventario.repository;

import pe.com.sinergiacafe.inventario.entity.InsumoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsumoRepository extends JpaRepository<InsumoEntity, Long> {
}
