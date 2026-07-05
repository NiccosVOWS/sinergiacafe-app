package pe.com.sinergiacafe.abastecimiento.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorRequestDTO {

    @NotBlank
    private String razonSocial;
    @NotBlank
    private String ruc;
    private String contacto;
    private String telefono;
    @Email
    private String correo;
    private String direccion;
    private Boolean activo;
}
