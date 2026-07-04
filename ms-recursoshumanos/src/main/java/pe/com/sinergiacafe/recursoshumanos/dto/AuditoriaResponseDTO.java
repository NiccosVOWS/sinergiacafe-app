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
    private Long idUsuario;
    private String modulo;
    private String accion;
    private String tablaAfectada;
    private Integer idRegistro;
    private String detalle;
    private java.time.LocalDateTime fechaHora;
}
