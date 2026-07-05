package pe.com.sinergiacafe.abastecimiento.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recepcion_mercaderia")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecepcionMercaderiaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recepcion_mercaderia")
    private Long id;

    @NotNull
    @Column(name = "id_orden_compra")
    private Long idOrdenCompra;
    @NotNull
    @Column(name = "id_empleado")
    private Long idEmpleado;
    @Column(name = "fecha_recepcion")
    private java.time.LocalDateTime fechaRecepcion;
    @Column(name = "conforme")
    private Boolean conforme;
    @Column(name = "observaciones")
    private String observaciones;
}
