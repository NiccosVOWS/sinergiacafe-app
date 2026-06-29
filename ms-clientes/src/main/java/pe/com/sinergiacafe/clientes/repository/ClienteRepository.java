package pe.com.sinergiacafe.clientes.repository;

import pe.com.sinergiacafe.clientes.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    Optional<ClienteEntity> findByDni(String dni);
    boolean existsByDni(String dni);
}