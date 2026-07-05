package pe.com.sinergiacafe.inventario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockResponseDTO {

    private Long idStock;
    private Long idInsumo;
    private BigDecimal cantidadActual;
    private BigDecimal cantidadMinima;
    private LocalDateTime ultimaActualizacion;
}