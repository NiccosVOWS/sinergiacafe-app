package pe.com.sinergiacafe.mantenimiento.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MantenimientoRequestDTO {

    @NotNull
    private Long equipoId;
    @NotNull
    private Long tipoMantenimientoId;
    private java.time.LocalDateTime fecha;
    private String responsable;
    private java.math.BigDecimal costo;
    private String observaciones;
}
