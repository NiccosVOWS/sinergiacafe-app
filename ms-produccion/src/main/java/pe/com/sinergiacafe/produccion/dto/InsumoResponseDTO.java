package pe.com.sinergiacafe.produccion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsumoResponseDTO {

    private Long idInsumo;
    private CategoriaInsumoResponseDTO categoriaInsumo;
    private String nombre;
    private String unidadMedida;
    private Boolean activo;
}