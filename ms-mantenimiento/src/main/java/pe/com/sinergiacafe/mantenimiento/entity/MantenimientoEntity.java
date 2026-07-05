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
    @Column(name = "id_mantenimiento")
    private Long id;

    @NotNull
    @Column(name = "id_equipo")
    private Long idEquipo;
    @NotNull
    @Column(name = "id_tipo_mantenimiento")
    private Long idTipoMantenimiento;
    @NotNull
    @Column(name = "id_empleado")
    private Long idEmpleado;
    @Column(name = "fecha_mantenimiento")
    private java.time.LocalDateTime fechaMantenimiento;
    @Column(name = "costo")
    private java.math.BigDecimal costo;
    @Column(name = "observaciones")
    private String observaciones;
}
