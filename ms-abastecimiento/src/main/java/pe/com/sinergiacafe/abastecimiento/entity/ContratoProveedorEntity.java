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
    private Long id;

    @NotNull
    private Long proveedorId;
    private java.time.LocalDate fechaInicio;
    private java.time.LocalDate fechaFin;
    private String condiciones;
    private String productosPactados;
}
