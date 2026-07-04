package pe.com.sinergiacafe.recursoshumanos.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsistenciaRequestDTO {

    @NotNull
    private Long empleadoId;
    @NotNull
    private java.time.LocalDate fecha;
    private java.time.LocalTime horaEntrada;
    private java.time.LocalTime horaSalida;
    private String observaciones;
}
