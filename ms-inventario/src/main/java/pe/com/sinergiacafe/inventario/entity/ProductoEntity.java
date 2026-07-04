package pe.com.sinergiacafe.inventario.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long id;

    @NotNull
    @Column(name = "id_categoria_producto")
    private Long idCategoriaProducto;
    @NotBlank
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @NotNull
    @Column(name = "precio")
    private java.math.BigDecimal precio;
    @Column(name = "disponible")
    private Boolean disponible;
}
