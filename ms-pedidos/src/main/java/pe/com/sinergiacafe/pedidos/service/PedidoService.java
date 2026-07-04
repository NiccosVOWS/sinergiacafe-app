package pe.com.sinergiacafe.pedidos.service;

import pe.com.sinergiacafe.pedidos.dto.PedidoRequestDTO;
import pe.com.sinergiacafe.pedidos.dto.PedidoResponseDTO;

import java.util.List;

public interface PedidoService {

    List<PedidoResponseDTO> listarTodos();
    PedidoResponseDTO obtenerPorId(Long id);
    List<PedidoResponseDTO> listarPorCliente(Long idCliente);
    PedidoResponseDTO crear(PedidoRequestDTO request);
    PedidoResponseDTO actualizarEstado(Long id, Long idEstadoPedido);
    void eliminar(Long id);
}