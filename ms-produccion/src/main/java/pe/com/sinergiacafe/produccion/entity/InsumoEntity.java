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
@Table(name = "insumo")
public class InsumoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_insumo")
    private Long idInsumo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categoria_insumo", nullable = false)
    private CategoriaInsumoEntity categoriaInsumo;

    @Column(name = "nombre", nullable = false, length = 120)
    private String nombre;

    @Column(name = "unidad_medida", nullable = false, length = 20)
    private String unidadMedida;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    @PrePersist
    public void prePersist() {
        if (activo == null) activo = true;
    }
}