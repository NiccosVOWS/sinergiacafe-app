package pe.com.sinergiacafe.recursoshumanos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "turno")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TurnoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_turno")
    private Long id;

    @NotBlank
    @Column(name = "nombre")
    private String nombre;
    @NotNull
    @Column(name = "hora_inicio")
    private java.time.LocalTime horaInicio;
    @NotNull
    @Column(name = "hora_fin")
    private java.time.LocalTime horaFin;
}
