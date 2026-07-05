package pe.com.sinergiacafe.inventario.service;

import pe.com.sinergiacafe.inventario.dto.MovimientoStockRequestDTO;
import pe.com.sinergiacafe.inventario.dto.MovimientoStockResponseDTO;

import java.util.List;

public interface MovimientoStockService {

    List<MovimientoStockResponseDTO> listarTodos();
    MovimientoStockResponseDTO obtenerPorId(Long id);
    MovimientoStockResponseDTO crear(MovimientoStockRequestDTO request);
    void eliminar(Long id);
}