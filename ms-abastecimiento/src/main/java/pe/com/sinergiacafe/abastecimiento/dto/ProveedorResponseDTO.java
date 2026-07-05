package pe.com.sinergiacafe.abastecimiento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorResponseDTO {

    private Long idProveedor;
    private String razonSocial;
    private String ruc;
    private String contacto;
    private String telefono;
    private String correo;
    private String direccion;
    private Boolean activo;
}