package pe.com.sinergiacafe.mantenimiento.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipoRequestDTO {

    @NotNull
    private Long idTipoEquipo;
    @NotBlank
    private String nombre;
    private String modelo;
    private java.time.LocalDate fechaAdquisicion;
    private String estado;
}
