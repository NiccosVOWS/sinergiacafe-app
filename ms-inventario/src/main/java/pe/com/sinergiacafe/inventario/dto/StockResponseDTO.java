package pe.com.sinergiacafe.inventario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockResponseDTO {

    private Long id;
    private Long idInsumo;
    private java.math.BigDecimal cantidadActual;
    private java.math.BigDecimal cantidadMinima;
    private java.time.LocalDateTime ultimaActualizacion;
}
