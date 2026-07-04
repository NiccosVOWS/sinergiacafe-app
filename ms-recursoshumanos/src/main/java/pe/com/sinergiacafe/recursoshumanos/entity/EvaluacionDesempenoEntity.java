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
    private Long id;

    @NotNull
    private Long empleadoId;
    private java.time.LocalDate fecha;
    private String criterios;
    private Integer puntaje;
    private String observaciones;
}
