package pe.com.sinergiacafe.pedidos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedido")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long idPedido;

    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "id_empleado", nullable = false)
    private Long idEmpleado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estado_pedido", nullable = false)
    private EstadoPedidoEntity estadoPedido;

    @Column(name = "fecha_pedido", nullable = false)
    private LocalDateTime fechaPedido;

    @Column(name = "observaciones", length = 300)
    private String observaciones;

    @PrePersist
    public void prePersist() {
        if (fechaPedido == null) fechaPedido = LocalDateTime.now();
    }
}