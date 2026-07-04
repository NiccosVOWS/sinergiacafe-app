package pe.com.sinergiacafe.recursoshumanos.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoRequestDTO {

    @NotBlank
    private String nombres;
    @NotBlank
    private String apellidos;
    @NotBlank
    private String dni;
    @NotNull
    private Long cargoId;
    private java.time.LocalDate fechaIngreso;
    private String estado;
}
