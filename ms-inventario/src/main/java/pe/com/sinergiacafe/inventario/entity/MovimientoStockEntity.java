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
    private Long id;

    @NotNull
    private Long stockId;
    @NotBlank
    private String tipoMovimiento;
    @NotNull
    private java.math.BigDecimal cantidad;
    private String motivo;
    private java.time.LocalDateTime fecha;
}
