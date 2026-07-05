package pe.com.sinergiacafe.mantenimiento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MantenimientoResponseDTO {

    private Long idMantenimiento;
    private EquipoResponseDTO equipo;
    private TipoMantenimientoResponseDTO tipoMantenimiento;
    private Long idEmpleado;
    private LocalDateTime fechaMantenimiento;
    private BigDecimal costo;
    private String observaciones;
}