package pe.com.sinergiacafe.finanzas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reporte_financiero")
public class ReporteFinancieroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reporte_financiero")
    private Long idReporteFinanciero;

    @Column(name = "periodo", nullable = false, length = 20)
    private String periodo;

    @Column(name = "ingresos", nullable = false, precision = 12, scale = 2)
    private BigDecimal ingresos;

    @Column(name = "egresos", nullable = false, precision = 12, scale = 2)
    private BigDecimal egresos;

    @Column(name = "utilidad_neta", nullable = false, precision = 12, scale = 2)
    private BigDecimal utilidadNeta;

    @Column(name = "fecha_generacion", nullable = false)
    private LocalDateTime fechaGeneracion;

    @PrePersist
    public void prePersist() {
        if (fechaGeneracion == null) {
            fechaGeneracion = LocalDateTime.now();
        }
    }
}
