package pe.com.sinergiacafe.ventas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComprobanteResponseDTO {

    private Long idComprobante;
    private Long idPedido;
    private TipoComprobanteResponseDTO tipoComprobante;
    private MetodoPagoResponseDTO metodoPago;
    private String serie;
    private String numero;
    private BigDecimal subtotal;
    private BigDecimal igv;
    private BigDecimal total;
    private LocalDateTime fechaEmision;
}