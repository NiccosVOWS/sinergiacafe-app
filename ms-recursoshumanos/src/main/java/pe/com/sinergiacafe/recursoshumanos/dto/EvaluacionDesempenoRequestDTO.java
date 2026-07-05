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
    private Long idEmpleado;
    @NotNull
    private java.time.LocalDate fechaEvaluacion;
    @NotNull
    private java.math.BigDecimal puntaje;
    private String criterios;
    private String observaciones;
}
