package pe.com.sinergiacafe.finanzas.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReporteFinancieroRequestDTO {

    @NotBlank(message = "El período es obligatorio.")
    @Size(max = 20, message = "El período no puede superar los 20 caracteres.")
    private String periodo;

    @NotNull(message = "Los ingresos son obligatorios.")
    @DecimalMin(value = "0.00", message = "Los ingresos no pueden ser negativos.")
    private BigDecimal ingresos;

    @NotNull(message = "Los egresos son obligatorios.")
    @DecimalMin(value = "0.00", message = "Los egresos no pueden ser negativos.")
    private BigDecimal egresos;

    @NotNull(message = "La utilidad neta es obligatoria.")
    private BigDecimal utilidadNeta;
}