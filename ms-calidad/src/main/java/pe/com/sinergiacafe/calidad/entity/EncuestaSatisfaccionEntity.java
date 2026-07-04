package pe.com.sinergiacafe.calidad.entity;

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
@Table(name = "encuesta_satisfaccion")
public class EncuestaSatisfaccionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_encuesta_satisfaccion")
    private Long idEncuestaSatisfaccion;

    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "fecha_encuesta", nullable = false)
    private LocalDateTime fechaEncuesta;

    @Column(name = "canal", nullable = false, length = 60)
    private String canal;

    @Column(name = "puntuacion_general", nullable = false, precision = 3, scale = 2)
    private BigDecimal puntuacionGeneral;

    @PrePersist
    public void prePersist() {
        if (fechaEncuesta == null) {
            fechaEncuesta = LocalDateTime.now();
        }
    }
}