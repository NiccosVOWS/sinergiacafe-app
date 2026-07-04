package pe.com.sinergiacafe.produccion.repository;

import pe.com.sinergiacafe.produccion.entity.CategoriaInsumoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaInsumoRepository extends JpaRepository<CategoriaInsumoEntity, Long> {

}