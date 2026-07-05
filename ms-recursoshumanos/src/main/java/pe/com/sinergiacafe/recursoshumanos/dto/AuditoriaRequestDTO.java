package pe.com.sinergiacafe.recursoshumanos.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditoriaRequestDTO {

    @NotNull(message = "El usuario es obligatorio")
    private Long idUsuario;

    @NotBlank(message = "El módulo es obligatorio")
    @Size(max = 80, message = "El módulo no puede superar 80 caracteres")
    private String modulo;

    @NotBlank(message = "La acción es obligatoria")
    @Size(max = 100, message = "La acción no puede superar 100 caracteres")
    private String accion;

    @Size(max = 80, message = "La tabla afectada no puede superar 80 caracteres")
    private String tablaAfectada;

    private Integer idRegistro;
    private String detalle;
}