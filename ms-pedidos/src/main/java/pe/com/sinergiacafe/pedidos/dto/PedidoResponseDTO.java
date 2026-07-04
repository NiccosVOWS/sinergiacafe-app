package pe.com.sinergiacafe.pedidos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoResponseDTO {

    private Long idPedido;
    private Long idCliente;
    private Long idEmpleado;
    private EstadoPedidoResponseDTO estadoPedido;
    private LocalDateTime fechaPedido;
    private String observaciones;
}