package pe.com.sinergiacafe.calidad.entity;

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
@Table(name = "respuesta_encuesta")
public class RespuestaEncuestaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_respuesta_encuesta")
    private Long idRespuestaEncuesta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_encuesta_satisfaccion", nullable = false)
    private EncuestaSatisfaccionEntity encuestaSatisfaccion;

    @Column(name = "pregunta", nullable = false, length = 300)
    private String pregunta;

    @Column(name = "puntuacion", nullable = false, precision = 3, scale = 2)
    private BigDecimal puntuacion;

    @Column(name = "comentario", length = 300)
    private String comentario;
}