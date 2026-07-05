package pe.com.sinergiacafe.finanzas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PresupuestoResponseDTO {

    private Long idPresupuesto;
    private String area;
    private String periodo;
    private BigDecimal montoAsignado;
    private BigDecimal montoEjecutado;
}