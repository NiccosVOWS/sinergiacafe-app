package pe.com.sinergiacafe.abastecimiento.dto;

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
public class DetalleOrdenCompraRequestDTO {

    @NotNull(message = "La orden de compra es obligatoria")
    private Long idOrdenCompra;

    @NotNull(message = "El insumo es obligatorio")
    private Long idInsumo;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a 0")
    private BigDecimal cantidad;

    @NotNull(message = "El precio unitario es obligatorio")
    @Positive(message = "El precio unitario debe ser mayor a 0")
    private BigDecimal precioUnitario;
}