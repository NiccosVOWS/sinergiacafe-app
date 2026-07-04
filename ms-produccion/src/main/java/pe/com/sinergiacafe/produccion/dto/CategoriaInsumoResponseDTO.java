package pe.com.sinergiacafe.produccion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaInsumoResponseDTO {

    private Long idCategoriaInsumo;
    private String nombre;
    private String descripcion;
}