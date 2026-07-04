package pe.com.sinergiacafe.abastecimiento.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecepcionMercaderiaRequestDTO {

    @NotNull
    private Long ordenCompraId;
    @NotNull
    private Long proveedorId;
    private java.time.LocalDateTime fecha;
    @NotNull
    private java.math.BigDecimal cantidadRecibida;
    private Boolean conforme;
}
