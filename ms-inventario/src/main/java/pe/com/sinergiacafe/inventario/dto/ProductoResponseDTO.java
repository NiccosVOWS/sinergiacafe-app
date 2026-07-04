package pe.com.sinergiacafe.inventario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoResponseDTO {

    private Long id;
    private Long idCategoriaProducto;
    private String nombre;
    private String descripcion;
    private java.math.BigDecimal precio;
    private Boolean disponible;
}
