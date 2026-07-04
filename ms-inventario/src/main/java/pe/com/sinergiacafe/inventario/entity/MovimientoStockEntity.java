package pe.com.sinergiacafe.inventario.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long id;

    @NotNull
    @Column(name = "id_stock")
    private Long idStock;
    @NotNull
    @Column(name = "id_empleado")
    private Long idEmpleado;
    @NotBlank
    @Column(name = "tipo_movimiento")
    private String tipoMovimiento;
    @NotNull
    @Column(name = "cantidad")
    private java.math.BigDecimal cantidad;
    @NotBlank
    @Column(name = "motivo")
    private String motivo;
    @Column(name = "fecha_movimiento")
    private java.time.LocalDateTime fechaMovimiento;
}
