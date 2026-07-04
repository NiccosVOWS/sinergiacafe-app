package pe.com.sinergiacafe.calidad.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class EncuestaSatisfaccionResponseDTO {

    private Long idEncuestaSatisfaccion;

    private Long idCliente;

    private LocalDateTime fechaEncuesta;

    private String canal;

    private BigDecimal puntuacionGeneral;
}
