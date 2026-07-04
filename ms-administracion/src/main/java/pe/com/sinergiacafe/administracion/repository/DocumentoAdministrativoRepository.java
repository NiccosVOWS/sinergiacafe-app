package pe.com.sinergiacafe.administracion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.sinergiacafe.administracion.entity.DocumentoAdministrativoEntity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DocumentoAdministrativoRepository extends JpaRepository<DocumentoAdministrativoEntity, Long>{
    // Buscar documentos por tipo
    List<DocumentoAdministrativoEntity> findByTipoDocumento(String tipoDocumento);

    // Buscar documentos por fecha
    List<DocumentoAdministrativoEntity> findByFechaDocumento(LocalDate fechaDocumento);

    // Buscar documentos por empleado
    List<DocumentoAdministrativoEntity> findByIdEmpleado(Integer idEmpleado);
}
