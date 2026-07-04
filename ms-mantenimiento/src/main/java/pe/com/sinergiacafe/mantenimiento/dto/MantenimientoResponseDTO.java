package pe.com.sinergiacafe.mantenimiento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MantenimientoResponseDTO {

    private Long id;
    private Long equipoId;
    private Long tipoMantenimientoId;
    private java.time.LocalDateTime fecha;
    private String responsable;
    private java.math.BigDecimal costo;
    private String observaciones;
}
