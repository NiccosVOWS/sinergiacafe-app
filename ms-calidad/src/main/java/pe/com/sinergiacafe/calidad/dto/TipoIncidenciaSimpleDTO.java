package pe.com.sinergiacafe.calidad.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TipoIncidenciaSimpleDTO {

    private Long idTipoIncidencia;
    private String nombre;
}