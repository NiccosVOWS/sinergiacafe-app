package pe.com.sinergiacafe.produccion.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecetaInsumoRequestDTO {

    @NotNull(message = "La receta es obligatoria")
    private Long idReceta;

    @NotNull(message = "El insumo es obligatorio")
    private Long idInsumo;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a 0")
    private BigDecimal cantidad;
}