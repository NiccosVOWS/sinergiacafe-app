package pe.com.sinergiacafe.abastecimiento.repository;

import pe.com.sinergiacafe.abastecimiento.entity.ContratoProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratoProveedorRepository extends JpaRepository<ContratoProveedorEntity, Long> {

    List<ContratoProveedorEntity> findByProveedor_IdProveedor(Long idProveedor);
    List<ContratoProveedorEntity> findByActivoTrue();
}