package pe.com.sinergiacafe.calidad.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoInspeccionRequestDTO {

    @NotNull(message = "La inspección es obligatoria")
    private Long idInspeccion;

    @NotNull(message = "El estándar de calidad es obligatorio")
    private Long idEstandarCalidad;

    @NotNull(message = "El campo cumple es obligatorio")
    private Boolean cumple;

    @Size(max = 300, message = "Las observaciones no pueden superar 300 caracteres")
    private String observaciones;
}