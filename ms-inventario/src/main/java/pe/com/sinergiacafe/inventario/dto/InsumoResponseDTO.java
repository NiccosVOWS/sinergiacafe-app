package pe.com.sinergiacafe.inventario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsumoResponseDTO {

    private Long id;
    private String nombre;
    private String unidadMedida;
    private String categoria;
    private java.math.BigDecimal stockActual;
    private java.math.BigDecimal stockMinimo;
}
