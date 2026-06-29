package pe.com.sinergiacafe.clientes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "programa_fidelizacion")
public class ProgramaFidelizacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_programa_fidelizacion")
    private Long idProgramaFidelizacion;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", length = 300)
    private String descripcion;

    @Column(name = "puntos_por_sol", nullable = false, precision = 5, scale = 2)
    private BigDecimal puntosPorSol;

    @Column(name = "min_puntos_canje", nullable = false)
    private Integer minPuntosCanje;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @PrePersist
    public void prePersist() {
        if (activo == null) activo = true;
    }
}