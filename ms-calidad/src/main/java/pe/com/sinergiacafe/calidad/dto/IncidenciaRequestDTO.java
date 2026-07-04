package pe.com.sinergiacafe.calidad.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import pe.com.sinergiacafe.calidad.entity.EstadoIncidencia;

@Data
public class IncidenciaRequestDTO {

    @NotNull(message = "El id del tipo de incidencia es obligatorio.")
    private Long idTipoIncidencia;

    @NotNull(message = "El id del empleado es obligatorio.")
    private Long idEmpleado;

    @NotBlank(message = "El área es obligatoria.")
    @Size(max = 80)
    private String area;

    @NotBlank(message = "La descripción es obligatoria.")
    @Size(max = 300)
    private String descripcion;

    @NotNull(message = "El estado es obligatorio.")
    private EstadoIncidencia estado;
}
