package pe.com.sinergiacafe.recursoshumanos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolPermisoId implements Serializable {
    private Long idRol;
    private Long idPermiso;
}
