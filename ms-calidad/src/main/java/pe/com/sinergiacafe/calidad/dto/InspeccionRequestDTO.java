package pe.com.sinergiacafe.calidad.dto;

import jakarta.validation.constraints.NotBlank;
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
public class InspeccionRequestDTO {

    @NotNull(message = "El empleado es obligatorio")
    private Long idEmpleado;

    @NotBlank(message = "El área inspeccionada es obligatoria")
    @Size(max = 80, message = "El área no puede superar 80 caracteres")
    private String areaInspeccionada;
}