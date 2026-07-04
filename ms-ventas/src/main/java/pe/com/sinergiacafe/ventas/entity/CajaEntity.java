package pe.com.sinergiacafe.ventas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "caja")
public class CajaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_caja")
    private Long idCaja;

    @Column(name = "id_empleado", nullable = false)
    private Long idEmpleado;

    @Column(name = "fecha_apertura", nullable = false)
    private LocalDateTime fechaApertura;

    @Column(name = "monto_inicial", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoInicial;

    @Column(name = "fecha_cierre")
    private LocalDateTime fechaCierre;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoCaja estado;

    public enum EstadoCaja {
        abierta, cerrada
    }

    @PrePersist
    public void prePersist() {
        if (fechaApertura == null) fechaApertura = LocalDateTime.now();
        if (estado == null) estado = EstadoCaja.abierta;
    }
}