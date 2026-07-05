package pe.com.sinergiacafe.recursoshumanos.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurnoRequestDTO {

    @NotBlank
    private String nombre;
    @NotNull
    private java.time.LocalTime horaInicio;
    @NotNull
    private java.time.LocalTime horaFin;
}
