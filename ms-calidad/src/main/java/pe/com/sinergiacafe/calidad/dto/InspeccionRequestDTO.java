package pe.com.sinergiacafe.calidad.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import pe.com.sinergiacafe.calidad.entity.ResultadoGeneralInspeccion;

@Data
public class InspeccionRequestDTO {

    @NotNull(message = "El id del empleado es obligatorio.")
    private Long idEmpleado;

    @NotBlank(message = "El área inspeccionada es obligatoria.")
    @Size(max = 80)
    private String areaInspeccionada;

    @NotNull(message = "El resultado general es obligatorio.")
    private ResultadoGeneralInspeccion resultadoGeneral;
}