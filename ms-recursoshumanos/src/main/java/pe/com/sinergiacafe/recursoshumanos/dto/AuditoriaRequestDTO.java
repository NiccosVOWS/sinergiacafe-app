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
    private Long usuarioId;
    @NotBlank
    private String accion;
    private java.time.LocalDateTime fecha;
    private String detalle;
}
