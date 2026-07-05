package pe.com.sinergiacafe.abastecimiento.service;

import pe.com.sinergiacafe.abastecimiento.dto.DetalleOrdenCompraRequestDTO;
import pe.com.sinergiacafe.abastecimiento.dto.DetalleOrdenCompraResponseDTO;

import java.util.List;

public interface DetalleOrdenCompraService {

    List<DetalleOrdenCompraResponseDTO> listarTodos();
    List<DetalleOrdenCompraResponseDTO> listarPorOrden(Long idOrdenCompra);
    DetalleOrdenCompraResponseDTO obtenerPorId(Long id);
    DetalleOrdenCompraResponseDTO agregar(DetalleOrdenCompraRequestDTO request);
    void eliminar(Long id);
}