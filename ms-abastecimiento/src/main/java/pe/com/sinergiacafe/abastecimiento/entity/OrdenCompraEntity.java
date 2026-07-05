package pe.com.sinergiacafe.abastecimiento.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orden_compra")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenCompraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden_compra")
    private Long idOrdenCompra;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_proveedor", nullable = false)
    private ProveedorEntity proveedor;

    @Column(name = "id_empleado", nullable = false)
    private Long idEmpleado;

    @Column(name = "fecha_orden", nullable = false)
    private LocalDateTime fechaOrden;

    @Column(name = "monto_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoTotal;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoOrden estado;

    public enum EstadoOrden {
        borrador, enviada, confirmada, recibida, cancelada
    }

    @PrePersist
    public void prePersist() {
        if (fechaOrden == null) fechaOrden = LocalDateTime.now();
        if (estado == null) estado = EstadoOrden.borrador;
        if (montoTotal == null) montoTotal = BigDecimal.ZERO;
    }
}