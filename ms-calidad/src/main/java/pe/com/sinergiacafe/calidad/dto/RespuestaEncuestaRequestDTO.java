package pe.com.sinergiacafe.calidad.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RespuestaEncuestaRequestDTO {

    @NotNull(message = "El id de la encuesta es obligatorio.")
    private Long idEncuestaSatisfaccion;

    @NotBlank(message = "La pregunta es obligatoria.")
    @Size(max = 300)
    private String pregunta;

    @NotNull(message = "La puntuación es obligatoria.")
    @DecimalMin(value = "0.00")
    private BigDecimal puntuacion;

    @Size(max = 300)
    private String comentario;
}