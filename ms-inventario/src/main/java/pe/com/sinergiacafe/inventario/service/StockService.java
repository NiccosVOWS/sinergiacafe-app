package pe.com.sinergiacafe.inventario.service;

import pe.com.sinergiacafe.inventario.dto.StockRequestDTO;
import pe.com.sinergiacafe.inventario.dto.StockResponseDTO;

import java.util.List;

public interface StockService {

    List<StockResponseDTO> listarTodos();
    StockResponseDTO obtenerPorId(Long id);
    StockResponseDTO crear(StockRequestDTO request);
    StockResponseDTO actualizar(Long id, StockRequestDTO request);
    void eliminar(Long id);
}