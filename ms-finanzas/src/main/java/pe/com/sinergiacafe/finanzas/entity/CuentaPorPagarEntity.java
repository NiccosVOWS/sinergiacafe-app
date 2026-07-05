package pe.com.sinergiacafe.finanzas.entity;

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
@Table(name = "cuenta_por_pagar")
public class CuentaPorPagarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta_por_pagar")
    private Long idCuentaPorPagar;

    @Column(name = "id_proveedor", nullable = false)
    private Long idProveedor;

    @Column(name = "id_orden_compra")
    private Long idOrdenCompra;

    @Column(name = "monto", nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoCuentaPorPagar estado;

    public enum EstadoCuentaPorPagar {
        pendiente, pagada, vencida
    }

    @PrePersist
    public void prePersist() {
        if (estado == null) estado = EstadoCuentaPorPagar.pendiente;
    }
}