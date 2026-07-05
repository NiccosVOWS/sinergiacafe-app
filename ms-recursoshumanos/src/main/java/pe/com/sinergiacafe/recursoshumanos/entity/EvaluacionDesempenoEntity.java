package pe.com.sinergiacafe.recursoshumanos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "evaluacion_desempeno")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluacionDesempenoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evaluacion_desempeno")
    private Long id;

    @NotNull
    @Column(name = "id_empleado")
    private Long idEmpleado;
    @NotNull
    @Column(name = "fecha_evaluacion")
    private java.time.LocalDate fechaEvaluacion;
    @NotNull
    @Column(name = "puntaje")
    private java.math.BigDecimal puntaje;
    @Column(name = "criterios")
    private String criterios;
    @Column(name = "observaciones")
    private String observaciones;
}
