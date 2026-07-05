package pe.com.sinergiacafe.recursoshumanos.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolPermisoRequestDTO {

    @NotNull(message = "El rol es obligatorio")
    private Long idRol;

    @NotNull(message = "El permiso es obligatorio")
    private Long idPermiso;
}