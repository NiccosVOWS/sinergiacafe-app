package pe.com.sinergiacafe.abastecimiento.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proveedor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProveedorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Long idProveedor;

    @Column(name = "razon_social", nullable = false, length = 200)
    private String razonSocial;

    @Column(name = "ruc", nullable = false, length = 11, unique = true)
    private String ruc;

    @Column(name = "contacto", length = 100)
    private String contacto;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "correo", length = 100)
    private String correo;

    @Column(name = "direccion", length = 300)
    private String direccion;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    @PrePersist
    public void prePersist() {
        if (activo == null) activo = true;
    }
}