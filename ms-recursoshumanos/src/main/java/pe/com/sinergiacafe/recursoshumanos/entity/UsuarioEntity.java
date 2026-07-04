package pe.com.sinergiacafe.recursoshumanos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @NotNull
    @Column(name = "id_empleado")
    private Long idEmpleado;
    @NotNull
    @Column(name = "id_rol")
    private Long idRol;
    @NotBlank
    @Column(name = "username")
    private String username;
    @NotBlank
    @Column(name = "password_hash")
    private String passwordHash;
    @Column(name = "activo")
    private Boolean activo;
    @Column(name = "fecha_creacion")
    private java.time.LocalDateTime fechaCreacion;
    @Column(name = "ultimo_acceso")
    private java.time.LocalDateTime ultimoAcceso;
}
