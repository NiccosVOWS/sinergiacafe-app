package pe.com.sinergiacafe.produccion.service;

import pe.com.sinergiacafe.produccion.dto.InsumoRequestDTO;
import pe.com.sinergiacafe.produccion.dto.InsumoResponseDTO;

import java.util.List;

public interface InsumoService {

    List<InsumoResponseDTO> listarTodos();
    InsumoResponseDTO obtenerPorId(Long id);
    InsumoResponseDTO crear(InsumoRequestDTO request);
    InsumoResponseDTO actualizar(Long id, InsumoRequestDTO request);
    void eliminar(Long id);
}