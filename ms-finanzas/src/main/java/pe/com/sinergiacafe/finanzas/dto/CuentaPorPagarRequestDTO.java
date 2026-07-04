package pe.com.sinergiacafe.finanzas.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pe.com.sinergiacafe.finanzas.entity.EstadoCuentaPorPagar;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CuentaPorPagarRequestDTO {

    @NotNull(message = "El proveedor es obligatorio.")
    private Long idProveedor;

    private Long idOrdenCompra;

    @NotNull(message = "El monto es obligatorio.")
    @DecimalMin(value = "0.00", inclusive = false,
            message = "El monto debe ser mayor a cero.")
    private BigDecimal monto;

    @NotNull(message = "La fecha de vencimiento es obligatoria.")
    private LocalDate fechaVencimiento;

    @NotNull(message = "El estado es obligatorio.")
    private EstadoCuentaPorPagar estado;
}