package pe.com.sinergiacafe.recursoshumanos.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoRequestDTO {

    @NotNull
    private Long idCargo;
    @NotNull
    private Long idTurno;
    @NotBlank
    private String nombres;
    @NotBlank
    private String apellidos;
    @NotBlank
    private String dni;
    private String telefono;
    @Email
    private String correo;
    @NotNull
    private java.time.LocalDate fechaIngreso;
    private String estadoLaboral;
}
