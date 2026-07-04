package pe.com.sinergiacafe.ventas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetodoPagoResponseDTO {

    private Long idMetodoPago;
    private String nombre;
    private String descripcion;
    private Boolean activo;
}