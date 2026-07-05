package pe.com.sinergiacafe.inventario.dto;

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
public class MovimientoStockRequestDTO {

    @NotNull(message = "El stock es obligatorio")
    private Long idStock;

    @NotNull(message = "El empleado es obligatorio")
    private Long idEmpleado;

    @NotNull(message = "El tipo de movimiento es obligatorio")
    private String tipoMovimiento;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a 0")
    private BigDecimal cantidad;

    @NotBlank(message = "El motivo es obligatorio")
    private String motivo;
}