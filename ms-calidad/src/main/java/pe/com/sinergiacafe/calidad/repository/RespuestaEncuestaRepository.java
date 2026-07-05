package pe.com.sinergiacafe.calidad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.sinergiacafe.calidad.entity.RespuestaEncuestaEntity;

import java.util.List;

@Repository
public interface RespuestaEncuestaRepository extends JpaRepository<RespuestaEncuestaEntity, Long> {

    List<RespuestaEncuestaEntity> findByEncuestaSatisfaccion_IdEncuestaSatisfaccion(Long idEncuestaSatisfaccion);
}