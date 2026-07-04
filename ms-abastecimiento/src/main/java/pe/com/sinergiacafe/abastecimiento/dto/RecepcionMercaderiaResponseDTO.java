package pe.com.sinergiacafe.abastecimiento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecepcionMercaderiaResponseDTO {

    private Long id;
    private Long ordenCompraId;
    private Long proveedorId;
    private java.time.LocalDateTime fecha;
    private java.math.BigDecimal cantidadRecibida;
    private Boolean conforme;
}
