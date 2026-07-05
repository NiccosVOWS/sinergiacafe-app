package pe.com.sinergiacafe.abastecimiento.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private Long idRecepcionMercaderia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_orden_compra", nullable = false)
    private OrdenCompraEntity ordenCompra;

    @Column(name = "id_empleado", nullable = false)
    private Long idEmpleado;

    @Column(name = "fecha_recepcion", nullable = false)
    private LocalDateTime fechaRecepcion;

    @Column(name = "conforme", nullable = false)
    private Boolean conforme;

    @Column(name = "observaciones", length = 300)
    private String observaciones;

    @PrePersist
    public void prePersist() {
        if (fechaRecepcion == null) fechaRecepcion = LocalDateTime.now();
        if (conforme == null) conforme = true;
    }
}