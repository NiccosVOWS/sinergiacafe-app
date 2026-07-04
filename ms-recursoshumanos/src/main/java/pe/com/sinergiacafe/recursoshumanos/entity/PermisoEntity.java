package pe.com.sinergiacafe.recursoshumanos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "permiso")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermisoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;
    private String modulo;
    private String accion;
}
