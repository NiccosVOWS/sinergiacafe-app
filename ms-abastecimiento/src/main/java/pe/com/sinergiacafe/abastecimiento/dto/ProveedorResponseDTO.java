package pe.com.sinergiacafe.abastecimiento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProveedorResponseDTO {

    private Long id;
    private String razonSocial;
    private String ruc;
    private String contacto;
    private String telefono;
    private String correo;
    private String direccion;
    private Boolean activo;
}
