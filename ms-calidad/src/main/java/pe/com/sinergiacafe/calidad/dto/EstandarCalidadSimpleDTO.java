package pe.com.sinergiacafe.calidad.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EstandarCalidadSimpleDTO {

    private Long idEstandarCalidad;
    private String nombre;
}
