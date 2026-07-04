package pe.com.sinergiacafe.calidad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.sinergiacafe.calidad.entity.EncuestaSatisfaccionEntity;

import java.util.List;

@Repository
public interface EncuestaSatisfaccionRepository extends JpaRepository<EncuestaSatisfaccionEntity, Long> {

    List<EncuestaSatisfaccionEntity> findByIdCliente(Long idCliente);

    List<EncuestaSatisfaccionEntity> findByCanal(String canal);
}