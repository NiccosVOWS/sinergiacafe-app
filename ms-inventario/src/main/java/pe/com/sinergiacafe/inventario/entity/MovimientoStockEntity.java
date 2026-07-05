package pe.com.sinergiacafe.inventario.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimiento_stock")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoStockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento_stock")
    private Long idMovimientoStock;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_stock", nullable = false)
    private StockEntity stock;

    @Column(name = "id_empleado", nullable = false)
    private Long idEmpleado;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimiento", nullable = false)
    private TipoMovimiento tipoMovimiento;

    @Column(name = "cantidad", nullable = false, precision = 10, scale = 3)
    private BigDecimal cantidad;

    @Column(name = "motivo", nullable = false, length = 200)
    private String motivo;

    @Column(name = "fecha_movimiento", nullable = false)
    private LocalDateTime fechaMovimiento;

    @PrePersist
    public void prePersist() {
        if (fechaMovimiento == null) fechaMovimiento = LocalDateTime.now();
    }

    public enum TipoMovimiento {
        entrada, salida
    }
}