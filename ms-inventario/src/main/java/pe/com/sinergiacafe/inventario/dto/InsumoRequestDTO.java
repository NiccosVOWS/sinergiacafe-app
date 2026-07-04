package pe.com.sinergiacafe.inventario.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsumoRequestDTO {

    @NotBlank
    private String nombre;
    @NotBlank
    private String unidadMedida;
    private String categoria;
    @NotNull
    private java.math.BigDecimal stockActual;
    @NotNull
    private java.math.BigDecimal stockMinimo;
}
