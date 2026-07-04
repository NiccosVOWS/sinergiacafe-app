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
    private Long id;

    @NotNull
    private Long empleadoId;
    @NotNull
    private java.time.LocalDate fecha;
    private java.time.LocalTime horaEntrada;
    private java.time.LocalTime horaSalida;
}
