package pe.com.sinergiacafe.mantenimiento.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipoRequestDTO {

    @NotNull(message = "El tipo de equipo es obligatorio")
    private Long idTipoEquipo;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 120, message = "El nombre no puede superar 120 caracteres")
    private String nombre;

    @Size(max = 100, message = "El modelo no puede superar 100 caracteres")
    private String modelo;

    private LocalDate fechaAdquisicion;
}