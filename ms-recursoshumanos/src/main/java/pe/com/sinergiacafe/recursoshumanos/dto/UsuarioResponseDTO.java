package pe.com.sinergiacafe.recursoshumanos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponseDTO {

    private Long id;
    private Long idEmpleado;
    private Long idRol;
    private String username;
    private String passwordHash;
    private Boolean activo;
    private java.time.LocalDateTime fechaCreacion;
    private java.time.LocalDateTime ultimoAcceso;
}
