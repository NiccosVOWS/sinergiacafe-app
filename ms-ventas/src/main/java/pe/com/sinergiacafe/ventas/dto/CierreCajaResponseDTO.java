package pe.com.sinergiacafe.ventas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CierreCajaResponseDTO {

    private Long idCierreCaja;
    private CajaResponseDTO caja;
    private MetodoPagoResponseDTO metodoPago;
    private BigDecimal montoRegistrado;
    private BigDecimal diferencia;
    private String observaciones;
}