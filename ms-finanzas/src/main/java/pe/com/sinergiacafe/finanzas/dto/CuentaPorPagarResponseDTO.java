package pe.com.sinergiacafe.finanzas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CuentaPorPagarResponseDTO {

    private Long idCuentaPorPagar;
    private Long idProveedor;
    private Long idOrdenCompra;
    private BigDecimal monto;
    private LocalDate fechaVencimiento;
    private String estado;
}