package pe.com.sinergiacafe.abastecimiento.dto;

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
public class OrdenCompraResponseDTO {

    private Long idOrdenCompra;
    private ProveedorResponseDTO proveedor;
    private Long idEmpleado;
    private LocalDateTime fechaOrden;
    private BigDecimal montoTotal;
    private String estado;
}