package pe.com.sinergiacafe.pedidos.service;

import pe.com.sinergiacafe.pedidos.dto.DetallePedidoRequestDTO;
import pe.com.sinergiacafe.pedidos.dto.DetallePedidoResponseDTO;

import java.util.List;

public interface DetallePedidoService {

    List<DetallePedidoResponseDTO> listarPorPedido(Long idPedido);
    DetallePedidoResponseDTO agregar(DetallePedidoRequestDTO request);
    void eliminar(Long id);
}