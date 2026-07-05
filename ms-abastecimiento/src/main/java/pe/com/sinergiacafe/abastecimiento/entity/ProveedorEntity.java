package pe.com.sinergiacafe.abastecimiento.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    private Long id;

    @NotBlank
    @Column(name = "razon_social")
    private String razonSocial;
    @NotBlank
    @Column(name = "ruc")
    private String ruc;
    @Column(name = "contacto")
    private String contacto;
    @Column(name = "telefono")
    private String telefono;
    @Email
    @Column(name = "correo")
    private String correo;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "activo")
    private Boolean activo;
}
