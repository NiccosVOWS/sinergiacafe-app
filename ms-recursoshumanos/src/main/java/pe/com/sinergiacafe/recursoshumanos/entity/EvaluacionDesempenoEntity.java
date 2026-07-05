package pe.com.sinergiacafe.recursoshumanos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

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
    private Long idEvaluacionDesempeno;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_empleado", nullable = false)
    private EmpleadoEntity empleado;

    @Column(name = "fecha_evaluacion", nullable = false)
    private LocalDate fechaEvaluacion;

    @Column(name = "puntaje", nullable = false, precision = 4, scale = 2)
    private BigDecimal puntaje;

    @Column(name = "criterios", length = 300)
    private String criterios;

    @Column(name = "observaciones", length = 300)
    private String observaciones;
}