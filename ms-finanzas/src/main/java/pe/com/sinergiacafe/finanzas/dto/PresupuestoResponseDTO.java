package pe.com.sinergiacafe.finanzas.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PresupuestoResponseDTO {

    private Long idPresupuesto;
    private String area;
    private String periodo;
    private BigDecimal montoAsignado;
    private BigDecimal montoEjecutado;

}