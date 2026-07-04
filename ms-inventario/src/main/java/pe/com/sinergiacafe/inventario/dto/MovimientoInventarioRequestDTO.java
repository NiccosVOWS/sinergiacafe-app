package pe.com.sinergiacafe.inventario.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoInventarioRequestDTO {

    @NotNull
    private Long insumoId;
    @NotBlank
    private String tipo;
    @NotNull
    private java.math.BigDecimal cantidad;
    private String motivo;
    private java.time.LocalDateTime fecha;
}
