package pe.com.sinergiacafe.produccion.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "receta_insumo")
public class RecetaInsumoEntity {

    @EmbeddedId
    private RecetaInsumoId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("idReceta")
    @JoinColumn(name = "id_receta")
    private RecetaEntity receta;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("idInsumo")
    @JoinColumn(name = "id_insumo")
    private InsumoEntity insumo;

    @Column(name = "cantidad", nullable = false, precision = 10, scale = 3)
    private BigDecimal cantidad;
}