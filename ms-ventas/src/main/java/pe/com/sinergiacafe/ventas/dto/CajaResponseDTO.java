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
public class CajaResponseDTO {

    private Long idCaja;
    private Long idEmpleado;
    private LocalDateTime fechaApertura;
    private BigDecimal montoInicial;
    private LocalDateTime fechaCierre;
    private String estado;
}