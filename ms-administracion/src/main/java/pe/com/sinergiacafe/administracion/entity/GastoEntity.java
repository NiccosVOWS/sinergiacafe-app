package pe.com.sinergiacafe.administracion.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gasto")
public class GastoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gasto")
    private Long idGasto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categoria_gasto", nullable = false)
    private CategoriaGastoEntity categoriaGasto;

    @Column(name = "fecha_gasto", nullable = false)
    private LocalDate fechaGasto;

    @Column(name = "monto", nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(name = "descripcion", nullable = false, length = 300)
    private String descripcion;

    @PrePersist
    public void prePersist() {
        if (fechaGasto == null) fechaGasto = LocalDate.now();
    }
}