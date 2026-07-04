package pe.com.sinergiacafe.recursoshumanos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "auditoria")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auditoria")
    private Long id;

    @NotNull
    @Column(name = "id_usuario")
    private Long idUsuario;
    @NotBlank
    @Column(name = "modulo")
    private String modulo;
    @NotBlank
    @Column(name = "accion")
    private String accion;
    @Column(name = "tabla_afectada")
    private String tablaAfectada;
    @Column(name = "id_registro")
    private Integer idRegistro;
    @Column(name = "detalle")
    private String detalle;
    @Column(name = "fecha_hora")
    private java.time.LocalDateTime fechaHora;
}
