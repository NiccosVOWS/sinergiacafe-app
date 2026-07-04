package pe.com.sinergiacafe.administracion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.sinergiacafe.administracion.entity.CategoriaGastoEntity;

import java.util.Optional;

@Repository
public interface CategoriaGastoRepository extends JpaRepository<CategoriaGastoEntity, Long> {

}
