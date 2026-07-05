package pe.com.sinergiacafe.inventario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResponseDTO {

    private Long idProducto;
    private CategoriaProductoResponseDTO categoriaProducto;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Boolean disponible;
}