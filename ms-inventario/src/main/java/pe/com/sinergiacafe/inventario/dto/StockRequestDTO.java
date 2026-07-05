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
public class StockRequestDTO {

    @NotNull(message = "El insumo es obligatorio")
    private Long idInsumo;

    @NotNull(message = "La cantidad actual es obligatoria")
    @PositiveOrZero(message = "La cantidad actual no puede ser negativa")
    private BigDecimal cantidadActual;

    @NotNull(message = "La cantidad mínima es obligatoria")
    @PositiveOrZero(message = "La cantidad mínima no puede ser negativa")
    private BigDecimal cantidadMinima;
}