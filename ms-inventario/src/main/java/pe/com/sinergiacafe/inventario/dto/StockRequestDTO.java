package pe.com.sinergiacafe.inventario.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockRequestDTO {

    @NotNull
    private Long idInsumo;
    @NotNull
    private java.math.BigDecimal cantidadActual;
    @NotNull
    private java.math.BigDecimal cantidadMinima;
}
