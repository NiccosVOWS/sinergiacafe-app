package pe.com.sinergiacafe.ventas.service;

import pe.com.sinergiacafe.ventas.dto.ComprobanteRequestDTO;
import pe.com.sinergiacafe.ventas.dto.ComprobanteResponseDTO;

import java.util.List;

public interface ComprobanteService {

    List<ComprobanteResponseDTO> listarTodos();
    ComprobanteResponseDTO obtenerPorId(Long id);
    List<ComprobanteResponseDTO> listarPorPedido(Long idPedido);
    ComprobanteResponseDTO emitir(ComprobanteRequestDTO request);
}