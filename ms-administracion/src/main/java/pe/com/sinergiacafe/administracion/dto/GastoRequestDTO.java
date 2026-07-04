package pe.com.sinergiacafe.administracion.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class GastoRequestDTO {

    @NotNull(message = "La categoría de gasto es obligatoria.")
    private Long idCategoriaGasto;

    @NotNull(message = "La fecha del gasto es obligatoria.")
    private LocalDate fechaGasto;

    @NotNull(message = "El monto es obligatorio.")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a cero.")
    private BigDecimal monto;

    @NotBlank(message = "La descripción es obligatoria.")
    private String descripcion;

}