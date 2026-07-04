package pe.com.sinergiacafe.produccion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecetaInsumoResponseDTO {

    private Long idReceta;
    private InsumoResponseDTO insumo;
    private BigDecimal cantidad;
}