package pe.com.sinergiacafe.abastecimiento.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orden_compra")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenCompraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long proveedorId;
    private java.time.LocalDateTime fecha;
    private String estado;
    @NotNull
    private java.math.BigDecimal montoTotal;
}
