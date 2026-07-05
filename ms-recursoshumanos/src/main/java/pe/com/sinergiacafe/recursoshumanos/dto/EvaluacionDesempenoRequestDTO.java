package pe.com.sinergiacafe.recursoshumanos.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionDesempenoRequestDTO {

    @NotNull(message = "El empleado es obligatorio")
    private Long idEmpleado;

    @NotNull(message = "La fecha de evaluación es obligatoria")
    private LocalDate fechaEvaluacion;

    @NotNull(message = "El puntaje es obligatorio")
    @DecimalMin(value = "0.00", message = "El puntaje no puede ser menor a 0")
    @DecimalMax(value = "5.00", message = "El puntaje no puede ser mayor a 5")
    private BigDecimal puntaje;

    @Size(max = 300, message = "Los criterios no pueden superar 300 caracteres")
    private String criterios;

    @Size(max = 300, message = "Las observaciones no pueden superar 300 caracteres")
    private String observaciones;
}