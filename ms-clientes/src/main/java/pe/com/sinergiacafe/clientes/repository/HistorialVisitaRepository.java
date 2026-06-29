package pe.com.sinergiacafe.clientes.repository;

import pe.com.sinergiacafe.clientes.entity.HistorialVisitaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialVisitaRepository extends JpaRepository<HistorialVisitaEntity, Long> {

    List<HistorialVisitaEntity> findByClienteIdCliente(Long idCliente);
}