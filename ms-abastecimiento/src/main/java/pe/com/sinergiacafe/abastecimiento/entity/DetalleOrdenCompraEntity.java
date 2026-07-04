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
    @Column(name = "id_detalle_orden_compra")
    private Long id;

    @NotNull
    @Column(name = "id_orden_compra")
    private Long idOrdenCompra;
    @NotNull
    @Column(name = "id_insumo")
    private Long idInsumo;
    @NotNull
    @Column(name = "cantidad")
    private java.math.BigDecimal cantidad;
    @NotNull
    @Column(name = "precio_unitario")
    private java.math.BigDecimal precioUnitario;
    @Column(name = "subtotal")
    private java.math.BigDecimal subtotal;
}
