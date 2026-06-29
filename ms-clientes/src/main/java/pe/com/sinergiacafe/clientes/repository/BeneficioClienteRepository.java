package pe.com.sinergiacafe.clientes.repository;

import pe.com.sinergiacafe.clientes.entity.BeneficioClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeneficioClienteRepository extends JpaRepository<BeneficioClienteEntity, Long> {

    List<BeneficioClienteEntity> findByClienteIdCliente(Long idCliente);
    Optional<BeneficioClienteEntity> findByClienteIdClienteAndProgramaFidelizacionIdProgramaFidelizacion(
            Long idCliente, Long idPrograma);
}