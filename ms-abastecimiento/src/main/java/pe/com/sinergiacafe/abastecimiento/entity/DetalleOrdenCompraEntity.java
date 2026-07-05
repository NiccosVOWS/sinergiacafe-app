package pe.com.sinergiacafe.abastecimiento.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
    private Long idDetalleOrdenCompra;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_orden_compra", nullable = false)
    private OrdenCompraEntity ordenCompra;

    @Column(name = "id_insumo", nullable = false)
    private Long idInsumo;

    @Column(name = "cantidad", nullable = false, precision = 10, scale = 3)
    private BigDecimal cantidad;

    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column(name = "subtotal", nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    @PrePersist
    public void prePersist() {
        if (subtotal == null && cantidad != null && precioUnitario != null) {
            subtotal = cantidad.multiply(precioUnitario);
        }
    }
}