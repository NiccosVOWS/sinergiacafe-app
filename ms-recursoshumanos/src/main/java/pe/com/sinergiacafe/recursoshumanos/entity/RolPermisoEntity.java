package pe.com.sinergiacafe.recursoshumanos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rol_permiso")
@IdClass(RolPermisoId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolPermisoEntity {

    @Id
    @Column(name = "id_rol")
    private Long idRol;
    @Id
    @Column(name = "id_permiso")
    private Long idPermiso;
}
