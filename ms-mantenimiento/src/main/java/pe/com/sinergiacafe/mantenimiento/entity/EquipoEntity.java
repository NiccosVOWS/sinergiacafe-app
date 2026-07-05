package pe.com.sinergiacafe.mantenimiento.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "equipo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipo")
    private Long idEquipo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_equipo", nullable = false)
    private TipoEquipoEntity tipoEquipo;

    @Column(name = "nombre", nullable = false, length = 120)
    private String nombre;

    @Column(name = "modelo", length = 100)
    private String modelo;

    @Column(name = "fecha_adquisicion")
    private LocalDate fechaAdquisicion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoEquipo estado;

    public enum EstadoEquipo {
        operativo, en_mantenimiento, inoperativo
    }

    @PrePersist
    public void prePersist() {
        if (estado == null) estado = EstadoEquipo.operativo;
    }
}