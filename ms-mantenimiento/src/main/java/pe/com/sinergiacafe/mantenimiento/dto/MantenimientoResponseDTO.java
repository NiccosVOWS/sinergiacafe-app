package pe.com.sinergiacafe.mantenimiento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MantenimientoResponseDTO {

    private Long id;
    private Long idEquipo;
    private Long idTipoMantenimiento;
    private Long idEmpleado;
    private java.time.LocalDateTime fechaMantenimiento;
    private java.math.BigDecimal costo;
    private String observaciones;
}
