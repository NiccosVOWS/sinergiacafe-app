package pe.com.sinergiacafe.clientes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoClienteResponseDTO {

    private Long idTipoCliente;
    private String nombre;
    private String descripcion;
    private BigDecimal descuentoPct;
    private Boolean activo;
}