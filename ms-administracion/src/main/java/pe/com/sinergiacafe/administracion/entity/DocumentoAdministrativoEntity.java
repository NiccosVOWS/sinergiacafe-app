package pe.com.sinergiacafe.administracion.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "documento_administrativo")
public class DocumentoAdministrativoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento_administrativo")
    private Long idDocumentoAdministrativo;

    @Column(name = "tipo_documento", nullable = false, length = 80)
    private String tipoDocumento;

    @Column(name = "fecha_documento", nullable = false)
    private LocalDate fechaDocumento;

    @Column(name = "descripcion", nullable = false, length = 300)
    private String descripcion;

    @Column(name = "id_empleado", nullable = false)
    private Long idEmpleado;

    @PrePersist
    public void prePersist() {
        if (fechaDocumento == null) fechaDocumento = LocalDate.now();
    }
}