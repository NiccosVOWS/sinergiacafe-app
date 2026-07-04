package pe.com.sinergiacafe.calidad.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class EncuestaSatisfaccionSimpleDTO {

    private Long idEncuestaSatisfaccion;
    private String canal;
    private BigDecimal puntuacionGeneral;
}