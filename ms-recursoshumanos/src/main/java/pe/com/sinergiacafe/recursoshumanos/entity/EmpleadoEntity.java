package pe.com.sinergiacafe.recursoshumanos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empleado")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpleadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Long id;

    @NotNull
    @Column(name = "id_cargo")
    private Long idCargo;
    @NotNull
    @Column(name = "id_turno")
    private Long idTurno;
    @NotBlank
    @Column(name = "nombres")
    private String nombres;
    @NotBlank
    @Column(name = "apellidos")
    private String apellidos;
    @NotBlank
    @Column(name = "dni")
    private String dni;
    @Column(name = "telefono")
    private String telefono;
    @Email
    @Column(name = "correo")
    private String correo;
    @NotNull
    @Column(name = "fecha_ingreso")
    private java.time.LocalDate fechaIngreso;
    @Column(name = "estado_laboral")
    private String estadoLaboral;
}
