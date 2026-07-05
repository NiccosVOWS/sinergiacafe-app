package pe.com.sinergiacafe.inventario.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categoria_producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria_producto")
    private Long idCategoriaProducto;

    @Column(name = "nombre", nullable = false, length = 80)
    private String nombre;

    @Column(name = "descripcion", length = 200)
    private String descripcion;
}