package pe.com.sinergiacafe.mantenimiento.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MantenimientoRequestDTO {

    @NotNull(message = "El equipo es obligatorio")
    private Long idEquipo;

    @NotNull(message = "El tipo de mantenimiento es obligatorio")
    private Long idTipoMantenimiento;

    @NotNull(message = "El empleado es obligatorio")
    private Long idEmpleado;

    @PositiveOrZero(message = "El costo no puede ser negativo")
    private BigDecimal costo;

    @Size(max = 300, message = "Las observaciones no pueden superar 300 caracteres")
    private String observaciones;
}