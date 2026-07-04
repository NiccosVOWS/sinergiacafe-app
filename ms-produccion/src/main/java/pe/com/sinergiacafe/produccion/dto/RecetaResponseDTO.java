package pe.com.sinergiacafe.produccion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecetaResponseDTO {

    private Long idReceta;
    private Long idProducto;
    private String instrucciones;
    private Integer tiempoPrepMin;
    private List<RecetaInsumoResponseDTO> insumos;
}