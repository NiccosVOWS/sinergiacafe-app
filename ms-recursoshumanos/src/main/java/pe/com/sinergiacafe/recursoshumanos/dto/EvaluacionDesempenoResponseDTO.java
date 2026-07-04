package pe.com.sinergiacafe.recursoshumanos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluacionDesempenoResponseDTO {

    private Long id;
    private Long empleadoId;
    private java.time.LocalDate fecha;
    private String criterios;
    private Integer puntaje;
    private String observaciones;
}
