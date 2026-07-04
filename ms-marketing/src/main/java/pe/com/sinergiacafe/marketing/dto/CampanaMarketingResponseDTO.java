package pe.com.sinergiacafe.marketing.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class CampanaMarketingResponseDTO {

    private Long idCampanaMarketing;
    private String nombre;
    private String descripcion;
    private String canal;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private BigDecimal presupuesto;
}