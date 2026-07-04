package pe.com.sinergiacafe.abastecimiento.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenCompraRequestDTO {

    @NotNull
    private Long proveedorId;
    private java.time.LocalDateTime fecha;
    private String estado;
    @NotNull
    private java.math.BigDecimal montoTotal;
}
