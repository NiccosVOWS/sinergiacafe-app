package pe.com.sinergiacafe.abastecimiento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetalleOrdenCompraResponseDTO {

    private Long idDetalleOrdenCompra;
    private Long idOrdenCompra;
    private Long idInsumo;
    private BigDecimal cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
}