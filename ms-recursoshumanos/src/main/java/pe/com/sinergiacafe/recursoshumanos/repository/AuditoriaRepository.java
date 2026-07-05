package pe.com.sinergiacafe.recursoshumanos.repository;

import pe.com.sinergiacafe.recursoshumanos.entity.AuditoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditoriaRepository extends JpaRepository<AuditoriaEntity, Long> {

    List<AuditoriaEntity> findByUsuario_IdUsuario(Long idUsuario);
    List<AuditoriaEntity> findByModulo(String modulo);
    List<AuditoriaEntity> findByModuloAndAccion(String modulo, String accion);
}