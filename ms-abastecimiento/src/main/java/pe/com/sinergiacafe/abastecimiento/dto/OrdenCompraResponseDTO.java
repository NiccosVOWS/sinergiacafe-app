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
    private Long idProveedor;
    private Long idEmpleado;
    private java.time.LocalDateTime fechaOrden;
    private java.math.BigDecimal montoTotal;
    private String estado;
}
