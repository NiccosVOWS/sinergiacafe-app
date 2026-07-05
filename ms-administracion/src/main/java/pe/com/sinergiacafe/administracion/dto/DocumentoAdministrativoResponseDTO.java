package pe.com.sinergiacafe.administracion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoAdministrativoResponseDTO {

    private Long idDocumentoAdministrativo;
    private String tipoDocumento;
    private LocalDate fechaDocumento;
    private String descripcion;
    private Long idEmpleado;
}