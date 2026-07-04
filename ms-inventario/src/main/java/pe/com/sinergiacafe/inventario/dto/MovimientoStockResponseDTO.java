package pe.com.sinergiacafe.inventario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoStockResponseDTO {

    private Long id;
    private Long stockId;
    private String tipoMovimiento;
    private java.math.BigDecimal cantidad;
    private String motivo;
    private java.time.LocalDateTime fecha;
}
