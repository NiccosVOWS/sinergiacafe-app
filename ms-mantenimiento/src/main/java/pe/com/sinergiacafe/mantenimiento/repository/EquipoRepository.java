package pe.com.sinergiacafe.mantenimiento.repository;

import pe.com.sinergiacafe.mantenimiento.entity.EquipoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipoRepository extends JpaRepository<EquipoEntity, Long> {

    List<EquipoEntity> findByEstado(EquipoEntity.EstadoEquipo estado);
    List<EquipoEntity> findByTipoEquipo_IdTipoEquipo(Long idTipoEquipo);
}