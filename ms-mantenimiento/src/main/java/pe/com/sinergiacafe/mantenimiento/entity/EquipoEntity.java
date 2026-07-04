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
    private Long id;

    @NotBlank
    private String nombre;
    private String modelo;
    private java.time.LocalDate fechaAdquisicion;
    private String estado;
    @NotNull
    private Long tipoEquipoId;
}
