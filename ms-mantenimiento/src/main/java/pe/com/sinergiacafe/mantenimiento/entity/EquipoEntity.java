package pe.com.sinergiacafe.mantenimiento.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "equipo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipo")
    private Long id;

    @NotNull
    @Column(name = "id_tipo_equipo")
    private Long idTipoEquipo;
    @NotBlank
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "fecha_adquisicion")
    private java.time.LocalDate fechaAdquisicion;
    @Column(name = "estado")
    private String estado;
}
