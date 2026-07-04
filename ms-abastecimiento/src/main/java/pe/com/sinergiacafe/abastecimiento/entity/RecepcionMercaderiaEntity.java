package pe.com.sinergiacafe.abastecimiento.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recepcion_mercaderia")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecepcionMercaderiaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long ordenCompraId;
    @NotNull
    private Long proveedorId;
    private java.time.LocalDateTime fecha;
    @NotNull
    private java.math.BigDecimal cantidadRecibida;
    private Boolean conforme;
}
