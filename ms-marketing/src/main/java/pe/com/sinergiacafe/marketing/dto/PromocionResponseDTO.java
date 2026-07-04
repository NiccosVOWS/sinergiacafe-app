package pe.com.sinergiacafe.marketing.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class PromocionResponseDTO {

    private Long idPromocion;

    private CampanaMarketingResponseDTO campanaMarketing;

    private String descripcion;

    private String tipoDescuento;

    private BigDecimal valorDescuento;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    private Boolean activo;
}