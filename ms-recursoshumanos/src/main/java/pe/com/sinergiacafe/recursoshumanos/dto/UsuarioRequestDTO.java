package pe.com.sinergiacafe.recursoshumanos.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO {

    @NotNull
    private Long empleadoId;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotNull
    private Long rolId;
    private String estado;
}
