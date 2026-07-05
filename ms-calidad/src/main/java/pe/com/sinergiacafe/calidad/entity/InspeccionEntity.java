package pe.com.sinergiacafe.calidad.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inspeccion")
public class InspeccionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inspeccion")
    private Long idInspeccion;

    @Column(name = "id_empleado", nullable = false)
    private Long idEmpleado;

    @Column(name = "fecha_inspeccion", nullable = false)
    private LocalDateTime fechaInspeccion;

    @Column(name = "area_inspeccionada", nullable = false, length = 80)
    private String areaInspeccionada;

    @Enumerated(EnumType.STRING)
    @Column(name = "resultado_general", nullable = false, length = 20)
    private ResultadoGeneralInspeccion resultadoGeneral;

    public enum ResultadoGeneralInspeccion {
        conforme, no_conforme, observado
    }

    @PrePersist
    public void prePersist() {
        if (fechaInspeccion == null) fechaInspeccion = LocalDateTime.now();
        if (resultadoGeneral == null) resultadoGeneral = ResultadoGeneralInspeccion.conforme;
    }
}