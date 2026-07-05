package pe.com.sinergiacafe.calidad.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "incidencia")
public class IncidenciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_incidencia")
    private Long idIncidencia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_incidencia", nullable = false)
    private TipoIncidenciaEntity tipoIncidencia;

    @Column(name = "id_empleado", nullable = false)
    private Long idEmpleado;

    @Column(name = "fecha_incidencia", nullable = false)
    private LocalDateTime fechaIncidencia;

    @Column(name = "area", nullable = false, length = 80)
    private String area;

    @Column(name = "descripcion", nullable = false, length = 300)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoIncidencia estado;

    public enum EstadoIncidencia {
        abierta, en_proceso, resuelta
    }

    @PrePersist
    public void prePersist() {
        if (fechaIncidencia == null) fechaIncidencia = LocalDateTime.now();
        if (estado == null) estado = EstadoIncidencia.abierta;
    }
}