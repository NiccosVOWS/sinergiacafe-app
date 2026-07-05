package pe.com.sinergiacafe.recursoshumanos.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermisoRequestDTO {

    @NotBlank(message = "El módulo es obligatorio")
    @Size(max = 80, message = "El módulo no puede superar 80 caracteres")
    private String modulo;

    @NotBlank(message = "La acción es obligatoria")
    @Size(max = 50, message = "La acción no puede superar 50 caracteres")
    private String accion;

    @Size(max = 200, message = "La descripción no puede superar 200 caracteres")
    private String descripcion;
}