package pe.com.sinergiacafe.recursoshumanos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditoriaResponseDTO {

    private Long id;
    private Long usuarioId;
    private String accion;
    private java.time.LocalDateTime fecha;
    private String detalle;
}
