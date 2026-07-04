package pe.com.sinergiacafe.inventario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoInventarioResponseDTO {

    private Long id;
    private Long insumoId;
    private String tipo;
    private java.math.BigDecimal cantidad;
    private String motivo;
    private java.time.LocalDateTime fecha;
}
