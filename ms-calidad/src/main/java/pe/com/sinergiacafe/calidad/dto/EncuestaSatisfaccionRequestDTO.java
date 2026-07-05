package pe.com.sinergiacafe.calidad.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EncuestaSatisfaccionRequestDTO {

    private Long idCliente;

    @NotBlank(message = "El canal es obligatorio")
    @Size(max = 60, message = "El canal no puede superar 60 caracteres")
    private String canal;

    @NotNull(message = "La puntuación general es obligatoria")
    @DecimalMin(value = "0.00", message = "La puntuación no puede ser negativa")
    @DecimalMax(value = "5.00", message = "La puntuación no puede superar 5")
    private BigDecimal puntuacionGeneral;
}