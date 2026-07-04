package pe.com.sinergiacafe.abastecimiento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenCompraResponseDTO {

    private Long id;
    private Long proveedorId;
    private java.time.LocalDateTime fecha;
    private String estado;
    private java.math.BigDecimal montoTotal;
}
