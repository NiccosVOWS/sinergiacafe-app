package pe.com.sinergiacafe.abastecimiento.repository;

import pe.com.sinergiacafe.abastecimiento.entity.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorEntity, Long> {

    Optional<ProveedorEntity> findByRuc(String ruc);
    boolean existsByRuc(String ruc);
    List<ProveedorEntity> findByActivoTrue();
}