package pe.com.sinergiacafe.finanzas.dto;

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
public class ReporteFinancieroResponseDTO {

    private Long idReporteFinanciero;
    private String periodo;
    private BigDecimal ingresos;
    private BigDecimal egresos;
    private BigDecimal utilidadNeta;
    private LocalDateTime fechaGeneracion;
}