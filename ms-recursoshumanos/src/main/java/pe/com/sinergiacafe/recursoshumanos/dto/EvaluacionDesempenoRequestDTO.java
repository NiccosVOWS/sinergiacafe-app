package pe.com.sinergiacafe.recursoshumanos.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionDesempenoRequestDTO {

    @NotNull
    private Long empleadoId;
    private java.time.LocalDate fecha;
    private String criterios;
    private Integer puntaje;
    private String observaciones;
}
