package pe.com.sinergiacafe.finanzas.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class ReporteFinancieroResponseDTO {

    private Long idReporteFinanciero;
    private String periodo;
    private BigDecimal ingresos;
    private BigDecimal egresos;
    private BigDecimal utilidadNeta;
    private LocalDateTime fechaGeneracion;
}