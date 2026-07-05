package pe.com.sinergiacafe.recursoshumanos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolPermisoRequestDTO {

    @jakarta.validation.constraints.NotNull
    private Long idRol;
    @jakarta.validation.constraints.NotNull
    private Long idPermiso;
}
