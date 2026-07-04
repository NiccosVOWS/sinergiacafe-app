package pe.com.sinergiacafe.calidad.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EstandarCalidadResponseDTO {

    private Long idEstandarCalidad;
    private String nombre;
    private String descripcion;
    private String aplicableA;
}