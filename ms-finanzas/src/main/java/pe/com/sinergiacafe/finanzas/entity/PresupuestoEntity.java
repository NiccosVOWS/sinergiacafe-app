package pe.com.sinergiacafe.finanzas.entity;

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
@Table(name = "presupuesto")
public class PresupuestoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_presupuesto")
    private Long idPresupuesto;

    @Column(name = "area", nullable = false, length = 80)
    private String area;

    @Column(name = "periodo", nullable = false, length = 20)
    private String periodo;

    @Column(name = "monto_asignado", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoAsignado;

    @Column(name = "monto_ejecutado", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoEjecutado;

    @PrePersist
    public void prePersist() {
        if (montoEjecutado == null) {
            montoEjecutado = BigDecimal.ZERO;
        }
    }
}