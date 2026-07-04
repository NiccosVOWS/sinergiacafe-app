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
    @Column(name = "id_orden_compra")
    private Long id;

    @NotNull
    @Column(name = "id_proveedor")
    private Long idProveedor;
    @NotNull
    @Column(name = "id_empleado")
    private Long idEmpleado;
    @Column(name = "fecha_orden")
    private java.time.LocalDateTime fechaOrden;
    @NotNull
    @Column(name = "monto_total")
    private java.math.BigDecimal montoTotal;
    @Column(name = "estado")
    private String estado;
}
