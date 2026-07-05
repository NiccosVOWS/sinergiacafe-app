package pe.com.sinergiacafe.inventario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaProductoResponseDTO {

    private Long idCategoriaProducto;
    private String nombre;
    private String descripcion;
}