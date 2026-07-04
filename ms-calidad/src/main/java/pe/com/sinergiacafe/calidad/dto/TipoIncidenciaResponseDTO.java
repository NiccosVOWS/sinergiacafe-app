package pe.com.sinergiacafe.calidad.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TipoIncidenciaResponseDTO {

    private Long idTipoIncidencia;
    private String nombre;
    private String descripcion;
}