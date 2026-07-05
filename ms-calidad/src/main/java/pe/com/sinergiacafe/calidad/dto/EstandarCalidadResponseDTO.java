package pe.com.sinergiacafe.calidad.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstandarCalidadResponseDTO {

    private Long idEstandarCalidad;
    private String nombre;
    private String descripcion;
    private String aplicableA;
}