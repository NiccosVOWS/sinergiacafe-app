package pe.com.sinergiacafe.abastecimiento.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private Long idContratoProveedor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_proveedor", nullable = false)
    private ProveedorEntity proveedor;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "condiciones", length = 300)
    private String condiciones;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    @PrePersist
    public void prePersist() {
        if (activo == null) activo = true;
    }
}