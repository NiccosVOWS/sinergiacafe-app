package pe.com.sinergiacafe.ventas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComprobanteRequestDTO {

    @NotNull(message = "El pedido es obligatorio")
    private Long idPedido;

    @NotNull(message = "El tipo de comprobante es obligatorio")
    private Long idTipoComprobante;

    @NotNull(message = "El método de pago es obligatorio")
    private Long idMetodoPago;

    @NotBlank(message = "La serie es obligatoria")
    private String serie;

    @NotBlank(message = "El número es obligatorio")
    private String numero;

    @NotNull(message = "El subtotal es obligatorio")
    @Positive(message = "El subtotal debe ser mayor a 0")
    private BigDecimal subtotal;

    @NotNull(message = "El IGV es obligatorio")
    @Positive(message = "El IGV debe ser mayor a 0")
    private BigDecimal igv;

    @NotNull(message = "El total es obligatorio")
    @Positive(message = "El total debe ser mayor a 0")
    private BigDecimal total;
}