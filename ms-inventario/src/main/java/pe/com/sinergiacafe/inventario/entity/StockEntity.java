package pe.com.sinergiacafe.inventario.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stock")
    private Long idStock;

    @Column(name = "id_insumo", nullable = false, unique = true)
    private Long idInsumo;

    @Column(name = "cantidad_actual", nullable = false, precision = 10, scale = 3)
    private BigDecimal cantidadActual;

    @Column(name = "cantidad_minima", nullable = false, precision = 10, scale = 3)
    private BigDecimal cantidadMinima;

    @Column(name = "ultima_actualizacion", nullable = false)
    private LocalDateTime ultimaActualizacion;

    @PrePersist
    @PreUpdate
    public void preUpdate() {
        ultimaActualizacion = LocalDateTime.now();
    }
}