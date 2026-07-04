package pe.com.sinergiacafe.marketing.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CampanaMarketingRequestDTO {

    private String nombre;
    private String descripcion;
    private String canal;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private BigDecimal presupuesto;
}