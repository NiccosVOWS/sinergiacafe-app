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
    private Long idProveedor;
    @NotNull
    private Long idEmpleado;
    @NotNull
    private java.math.BigDecimal montoTotal;
    private String estado;
}
