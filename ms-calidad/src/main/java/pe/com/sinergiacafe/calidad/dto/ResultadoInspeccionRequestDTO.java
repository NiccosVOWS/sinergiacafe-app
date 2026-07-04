package pe.com.sinergiacafe.calidad.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResultadoInspeccionRequestDTO {

    @NotNull(message = "El id de inspección es obligatorio.")
    private Long idInspeccion;

    @NotNull(message = "El id del estándar de calidad es obligatorio.")
    private Long idEstandarCalidad;

    @NotNull(message = "El campo cumple es obligatorio.")
    private Boolean cumple;

    @Size(max = 300)
    private String observaciones;
}