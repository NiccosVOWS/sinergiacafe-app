package pe.com.sinergiacafe.ventas.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CierreCajaRequestDTO {

    @NotNull(message = "La caja es obligatoria")
    private Long idCaja;

    @NotNull(message = "El método de pago es obligatorio")
    private Long idMetodoPago;

    @NotNull(message = "El monto registrado es obligatorio")
    @PositiveOrZero(message = "El monto registrado no puede ser negativo")
    private BigDecimal montoRegistrado;

    private BigDecimal diferencia;
    private String observaciones;
}