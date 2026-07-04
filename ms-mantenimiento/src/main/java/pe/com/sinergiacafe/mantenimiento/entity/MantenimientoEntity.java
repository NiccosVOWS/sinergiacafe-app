package pe.com.sinergiacafe.mantenimiento.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mantenimiento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MantenimientoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long equipoId;
    @NotNull
    private Long tipoMantenimientoId;
    private java.time.LocalDateTime fecha;
    private String responsable;
    private java.math.BigDecimal costo;
    private String observaciones;
}
