package pe.com.sinergiacafe.recursoshumanos.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditoriaRequestDTO {

    @NotNull
    private Long idUsuario;
    @NotBlank
    private String modulo;
    @NotBlank
    private String accion;
    private String tablaAfectada;
    private Integer idRegistro;
    private String detalle;
}
