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
    private Long id;

    @NotNull
    private Long usuarioId;
    @NotBlank
    private String accion;
    private java.time.LocalDateTime fecha;
    private String detalle;
}
