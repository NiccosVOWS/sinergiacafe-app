package pe.com.sinergiacafe.recursoshumanos.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolRequestDTO {

    @NotBlank
    private String nombre;
    private String descripcion;
}
