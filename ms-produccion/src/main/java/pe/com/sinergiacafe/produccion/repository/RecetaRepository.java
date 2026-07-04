package pe.com.sinergiacafe.produccion.repository;

import pe.com.sinergiacafe.produccion.entity.RecetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecetaRepository extends JpaRepository<RecetaEntity, Long> {

    Optional<RecetaEntity> findByIdProducto(Long idProducto);
    boolean existsByIdProducto(Long idProducto);
}