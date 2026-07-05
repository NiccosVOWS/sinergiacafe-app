package pe.com.sinergiacafe.recursoshumanos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rol_permiso")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolPermisoEntity {

    @EmbeddedId
    private RolPermisoId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("idRol")
    @JoinColumn(name = "id_rol")
    private RolEntity rol;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("idPermiso")
    @JoinColumn(name = "id_permiso")
    private PermisoEntity permiso;
}