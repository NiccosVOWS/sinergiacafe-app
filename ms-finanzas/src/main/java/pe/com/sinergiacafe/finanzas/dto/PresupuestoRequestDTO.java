package pe.com.sinergiacafe.finanzas.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PresupuestoRequestDTO {

    @NotBlank(message = "El área es obligatoria.")
    @Size(max = 80, message = "El área no puede superar los 80 caracteres.")
    private String area;

    @NotBlank(message = "El período es obligatorio.")
    @Size(max = 20, message = "El período no puede superar los 20 caracteres.")
    private String periodo;

    @NotNull(message = "El monto asignado es obligatorio.")
    @DecimalMin(value = "0.00", inclusive = false,
            message = "El monto asignado debe ser mayor a cero.")
    private BigDecimal montoAsignado;

    @NotNull(message = "El monto ejecutado es obligatorio.")
    @DecimalMin(value = "0.00",
            message = "El monto ejecutado no puede ser negativo.")
    private BigDecimal montoEjecutado;

}