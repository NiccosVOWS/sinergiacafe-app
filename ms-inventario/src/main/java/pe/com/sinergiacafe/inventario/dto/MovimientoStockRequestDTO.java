package pe.com.sinergiacafe.inventario.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoStockRequestDTO {

    @NotNull
    private Long idStock;
    @NotNull
    private Long idEmpleado;
    @NotBlank
    private String tipoMovimiento;
    @NotNull
    private java.math.BigDecimal cantidad;
    @NotBlank
    private String motivo;
}
