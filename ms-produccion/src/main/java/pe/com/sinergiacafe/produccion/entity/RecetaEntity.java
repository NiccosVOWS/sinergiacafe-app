package pe.com.sinergiacafe.produccion.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "receta")
public class RecetaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_receta")
    private Long idReceta;

    @Column(name = "id_producto", nullable = false, unique = true)
    private Long idProducto;

    @Column(name = "instrucciones", nullable = false, columnDefinition = "TEXT")
    private String instrucciones;

    @Column(name = "tiempo_prep_min", nullable = false)
    private Integer tiempoPrepMin;
}