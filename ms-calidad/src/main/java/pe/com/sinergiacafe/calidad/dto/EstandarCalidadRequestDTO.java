package pe.com.sinergiacafe.calidad.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EstandarCalidadRequestDTO {

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(max = 120)
    private String nombre;

    @Size(max = 300)
    private String descripcion;

    @NotBlank(message = "aplicable_a es obligatorio.")
    @Size(max = 80)
    private String aplicableA;
}