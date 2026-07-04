package pe.com.sinergiacafe.ventas.entity;

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
@Table(name = "cierre_caja")
public class CierreCajaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cierre_caja")
    private Long idCierreCaja;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_caja", nullable = false)
    private CajaEntity caja;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_metodo_pago", nullable = false)
    private MetodoPagoEntity metodoPago;

    @Column(name = "monto_registrado", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoRegistrado;

    @Column(name = "diferencia", nullable = false, precision = 10, scale = 2)
    private BigDecimal diferencia;

    @Column(name = "observaciones", length = 300)
    private String observaciones;

    @PrePersist
    public void prePersist() {
        if (diferencia == null) diferencia = BigDecimal.ZERO;
    }
}