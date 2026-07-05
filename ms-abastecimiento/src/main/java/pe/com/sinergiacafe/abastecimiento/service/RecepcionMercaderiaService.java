package pe.com.sinergiacafe.abastecimiento.service;

import pe.com.sinergiacafe.abastecimiento.dto.RecepcionMercaderiaRequestDTO;
import pe.com.sinergiacafe.abastecimiento.dto.RecepcionMercaderiaResponseDTO;

import java.util.List;

public interface RecepcionMercaderiaService {

    List<RecepcionMercaderiaResponseDTO> listarTodos();
    List<RecepcionMercaderiaResponseDTO> listarPorOrden(Long idOrdenCompra);
    RecepcionMercaderiaResponseDTO obtenerPorId(Long id);
    RecepcionMercaderiaResponseDTO registrar(RecepcionMercaderiaRequestDTO request);
    void eliminar(Long id);
}