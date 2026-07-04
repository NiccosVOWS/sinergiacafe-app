package pe.com.sinergiacafe.mantenimiento.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipo_equipo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoEquipoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;
    private String descripcion;
}
