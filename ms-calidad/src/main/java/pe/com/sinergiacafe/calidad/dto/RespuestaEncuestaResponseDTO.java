package pe.com.sinergiacafe.calidad.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class RespuestaEncuestaResponseDTO {

    private Long idRespuestaEncuesta;

    private EncuestaSatisfaccionSimpleDTO encuestaSatisfaccion;

    private String pregunta;

    private BigDecimal puntuacion;

    private String comentario;
}