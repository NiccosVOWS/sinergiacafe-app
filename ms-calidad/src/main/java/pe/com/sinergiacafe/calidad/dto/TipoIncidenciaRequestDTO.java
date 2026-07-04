package pe.com.sinergiacafe.calidad.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TipoIncidenciaRequestDTO {

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(max = 80)
    private String nombre;

    @Size(max = 200)
    private String descripcion;
}
