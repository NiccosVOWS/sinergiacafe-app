package pe.com.sinergiacafe.recursoshumanos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "asistencia")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AsistenciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asistencia")
    private Long id;

    @NotNull
    @Column(name = "id_empleado")
    private Long idEmpleado;
    @NotNull
    @Column(name = "fecha")
    private java.time.LocalDate fecha;
    @Column(name = "hora_entrada")
    private java.time.LocalTime horaEntrada;
    @Column(name = "hora_salida")
    private java.time.LocalTime horaSalida;
    @Column(name = "observaciones")
    private String observaciones;
}
