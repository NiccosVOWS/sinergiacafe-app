package pe.com.sinergiacafe.clientes.repository;

import pe.com.sinergiacafe.clientes.entity.TipoClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoClienteRepository extends JpaRepository<TipoClienteEntity, Long> {

}