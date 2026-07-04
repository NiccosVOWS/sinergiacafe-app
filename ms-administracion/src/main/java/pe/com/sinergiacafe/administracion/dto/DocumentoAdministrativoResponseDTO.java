package pe.com.sinergiacafe.administracion.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class DocumentoAdministrativoResponseDTO {

    private Long idDocumentoAdministrativo;
    private String tipoDocumento;
    private LocalDate fechaDocumento;
    private String descripcion;
    private Integer idEmpleado;
}
