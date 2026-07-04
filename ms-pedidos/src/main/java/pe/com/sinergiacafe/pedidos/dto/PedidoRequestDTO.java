package pe.com.sinergiacafe.pedidos.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequestDTO {

    private Long idCliente;

    @NotNull(message = "El empleado es obligatorio")
    private Long idEmpleado;

    @NotNull(message = "El estado del pedido es obligatorio")
    private Long idEstadoPedido;

    private String observaciones;
}