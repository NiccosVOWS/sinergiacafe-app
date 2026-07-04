package pe.com.sinergiacafe.abastecimiento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleOrdenCompraResponseDTO {

    private Long id;
    private Long ordenCompraId;
    private Long insumoId;
    private java.math.BigDecimal cantidad;
    private java.math.BigDecimal precioUnitario;
}
