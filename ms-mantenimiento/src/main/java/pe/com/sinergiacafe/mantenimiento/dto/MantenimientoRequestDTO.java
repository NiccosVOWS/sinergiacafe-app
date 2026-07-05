package pe.com.sinergiacafe.mantenimiento.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MantenimientoRequestDTO {

    @NotNull
    private Long idEquipo;
    @NotNull
    private Long idTipoMantenimiento;
    @NotNull
    private Long idEmpleado;
    private java.math.BigDecimal costo;
    private String observaciones;
}
