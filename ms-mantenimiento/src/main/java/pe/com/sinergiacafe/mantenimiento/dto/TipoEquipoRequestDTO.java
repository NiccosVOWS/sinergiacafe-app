package pe.com.sinergiacafe.mantenimiento.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoEquipoRequestDTO {

    @NotBlank
    private String nombre;
    private String descripcion;
}
