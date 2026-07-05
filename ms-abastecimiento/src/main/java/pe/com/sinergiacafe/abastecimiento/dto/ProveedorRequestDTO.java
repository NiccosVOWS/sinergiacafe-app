package pe.com.sinergiacafe.abastecimiento.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorRequestDTO {

    @NotBlank(message = "La razón social es obligatoria")
    @Size(max = 200, message = "La razón social no puede superar 200 caracteres")
    private String razonSocial;

    @NotBlank(message = "El RUC es obligatorio")
    @Size(min = 11, max = 11, message = "El RUC debe tener 11 caracteres")
    private String ruc;

    @Size(max = 100, message = "El contacto no puede superar 100 caracteres")
    private String contacto;

    @Size(max = 15, message = "El teléfono no puede superar 15 caracteres")
    private String telefono;

    @Email(message = "El correo no tiene un formato válido")
    private String correo;

    @Size(max = 300, message = "La dirección no puede superar 300 caracteres")
    private String direccion;
}