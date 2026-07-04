package pe.com.sinergiacafe.finanzas.dto;

import lombok.Builder;
import lombok.Data;
import pe.com.sinergiacafe.finanzas.entity.EstadoCuentaPorPagar;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class CuentaPorPagarResponseDTO {

    private Long idCuentaPorPagar;
    private Long idProveedor;
    private Long idOrdenCompra;
    private BigDecimal monto;
    private LocalDate fechaVencimiento;
    private EstadoCuentaPorPagar estado;
}