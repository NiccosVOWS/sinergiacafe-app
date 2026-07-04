package pe.com.sinergiacafe.marketing.dto;

import lombok.Data;
import pe.com.sinergiacafe.marketing.entity.TipoDescuento;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PromocionRequestDTO {

    private Long idCampanaMarketing;

    private String descripcion;

    private TipoDescuento tipoDescuento;

    private BigDecimal valorDescuento;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;
}