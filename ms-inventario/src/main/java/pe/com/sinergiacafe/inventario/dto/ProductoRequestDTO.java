package pe.com.sinergiacafe.inventario.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoRequestDTO {

    @NotNull
    private Long idCategoriaProducto;
    @NotBlank
    private String nombre;
    private String descripcion;
    @NotNull
    private java.math.BigDecimal precio;
    private Boolean disponible;
}
