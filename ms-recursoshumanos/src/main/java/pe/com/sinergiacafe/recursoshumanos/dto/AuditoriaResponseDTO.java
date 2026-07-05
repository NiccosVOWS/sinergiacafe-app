package pe.com.sinergiacafe.recursoshumanos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditoriaResponseDTO {

    private Long idAuditoria;
    private UsuarioResponseDTO usuario;
    private String modulo;
    private String accion;
    private String tablaAfectada;
    private Integer idRegistro;
    private String detalle;
    private LocalDateTime fechaHora;
}