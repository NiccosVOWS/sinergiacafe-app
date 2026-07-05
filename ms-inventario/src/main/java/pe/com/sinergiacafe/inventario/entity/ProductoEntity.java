package pe.com.sinergiacafe.inventario.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categoria_producto", nullable = false)
    private CategoriaProductoEntity categoriaProducto;

    @Column(name = "nombre", nullable = false, length = 120)
    private String nombre;

    @Column(name = "descripcion", length = 300)
    private String descripcion;

    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "disponible", nullable = false)
    private Boolean disponible;

    @PrePersist
    public void prePersist() {
        if (disponible == null) disponible = true;
    }
}