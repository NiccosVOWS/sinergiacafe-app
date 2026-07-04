package pe.com.sinergiacafe.administracion.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoriaGastoResponseDTO {

    private Long idCategoriaGasto;
    private String nombre;
    private String descripcion;
}
