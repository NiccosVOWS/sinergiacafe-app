package pe.com.sinergiacafe.inventario.dto;

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
public class MovimientoStockResponseDTO {

    private Long idMovimientoStock;
    private Long idStock;
    private Long idEmpleado;
    private String tipoMovimiento;
    private BigDecimal cantidad;
    private String motivo;
    private LocalDateTime fechaMovimiento;
}