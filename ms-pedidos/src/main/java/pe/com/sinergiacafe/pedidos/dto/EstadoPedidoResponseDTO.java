package pe.com.sinergiacafe.pedidos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoPedidoResponseDTO {

    private Long idEstadoPedido;
    private String nombre;
    private String descripcion;
}