package pe.com.sinergiacafe.recursoshumanos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long rolId;
    @NotNull
    private Long permisoId;
}
