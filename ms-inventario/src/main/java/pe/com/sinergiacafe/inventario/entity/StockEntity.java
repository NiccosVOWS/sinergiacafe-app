package pe.com.sinergiacafe.inventario.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long id;

    @NotNull
    @Column(name = "id_insumo")
    private Long idInsumo;
    @NotNull
    @Column(name = "cantidad_actual")
    private java.math.BigDecimal cantidadActual;
    @NotNull
    @Column(name = "cantidad_minima")
    private java.math.BigDecimal cantidadMinima;
    @Column(name = "ultima_actualizacion")
    private java.time.LocalDateTime ultimaActualizacion;
}
