package pe.com.sinergiacafe.abastecimiento.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detalle_orden_compra")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleOrdenCompraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long ordenCompraId;
    @NotNull
    private Long insumoId;
    @NotNull
    private java.math.BigDecimal cantidad;
    @NotNull
    private java.math.BigDecimal precioUnitario;
}
