package pe.com.sinergiacafe.abastecimiento.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contrato_proveedor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContratoProveedorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrato_proveedor")
    private Long id;

    @NotNull
    @Column(name = "id_proveedor")
    private Long idProveedor;
    @NotNull
    @Column(name = "fecha_inicio")
    private java.time.LocalDate fechaInicio;
    @Column(name = "fecha_fin")
    private java.time.LocalDate fechaFin;
    @Column(name = "condiciones")
    private String condiciones;
    @Column(name = "activo")
    private Boolean activo;
}
