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
    private Long idEmpleado;
    @NotNull
    private Long idRol;
    @NotBlank
    private String username;
    @NotBlank
    private String passwordHash;
    private Boolean activo;
    private java.time.LocalDateTime ultimoAcceso;
}
