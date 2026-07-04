package pe.com.sinergiacafe.calidad.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EncuestaSatisfaccionRequestDTO {

    @NotNull(message = "El id del cliente es obligatorio.")
    private Long idCliente;

    @NotBlank(message = "El canal es obligatorio.")
    @Size(max = 60)
    private String canal;

    @NotNull(message = "La puntuación general es obligatoria.")
    @DecimalMin(value = "0.00")
    private BigDecimal puntuacionGeneral;
}