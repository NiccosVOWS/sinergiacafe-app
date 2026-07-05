package pe.com.sinergiacafe.administracion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaGastoResponseDTO {

    private Long idCategoriaGasto;
    private String nombre;
    private String descripcion;
}