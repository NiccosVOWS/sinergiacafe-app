package pe.com.sinergiacafe.calidad.entity;

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
@Table(name = "resultado_inspeccion")
public class ResultadoInspeccionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resultado_inspeccion")
    private Long idResultadoInspeccion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_inspeccion", nullable = false)
    private InspeccionEntity inspeccion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estandar_calidad", nullable = false)
    private EstandarCalidadEntity estandarCalidad;

    @Column(name = "cumple", nullable = false)
    private Boolean cumple;

    @Column(name = "observaciones", length = 300)
    private String observaciones;

    @PrePersist
    public void prePersist() {
        if (cumple == null) {
            cumple = true;
        }
    }
}