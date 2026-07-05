package pe.com.sinergiacafe.calidad.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaEncuestaResponseDTO {

    private Long idRespuestaEncuesta;
    private EncuestaSatisfaccionResponseDTO encuestaSatisfaccion;
    private String pregunta;
    private BigDecimal puntuacion;
    private String comentario;
}