package pe.com.sinergiacafe.clientes.entity;

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
@Table(name = "beneficio_cliente")
public class BeneficioClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_beneficio_cliente")
    private Long idBeneficioCliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente", nullable = false)
    private ClienteEntity cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_programa_fidelizacion", nullable = false)
    private ProgramaFidelizacionEntity programaFidelizacion;

    @Column(name = "puntos_acumulados", nullable = false)
    private Integer puntosAcumulados;

    @Column(name = "puntos_canjeados", nullable = false)
    private Integer puntosCanjeados;

    @Column(name = "puntos_disponibles", nullable = false)
    private Integer puntosDisponibles;

    @Column(name = "ultima_actualizacion", nullable = false)
    private LocalDateTime ultimaActualizacion;

    @PrePersist
    @PreUpdate
    public void preUpdate() {
        ultimaActualizacion = LocalDateTime.now();
    }
}