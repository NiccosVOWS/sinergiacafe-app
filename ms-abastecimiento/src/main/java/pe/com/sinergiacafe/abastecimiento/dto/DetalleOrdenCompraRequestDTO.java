package pe.com.sinergiacafe.abastecimiento.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleOrdenCompraRequestDTO {

    @NotNull
    private Long ordenCompraId;
    @NotNull
    private Long insumoId;
    @NotNull
    private java.math.BigDecimal cantidad;
    @NotNull
    private java.math.BigDecimal precioUnitario;
}
