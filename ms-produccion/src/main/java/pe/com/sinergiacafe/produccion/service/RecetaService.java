package pe.com.sinergiacafe.produccion.service;

import pe.com.sinergiacafe.produccion.dto.RecetaRequestDTO;
import pe.com.sinergiacafe.produccion.dto.RecetaResponseDTO;

import java.util.List;

public interface RecetaService {

    List<RecetaResponseDTO> listarTodos();
    RecetaResponseDTO obtenerPorId(Long id);
    RecetaResponseDTO obtenerPorProducto(Long idProducto);
    RecetaResponseDTO crear(RecetaRequestDTO request);
    RecetaResponseDTO actualizar(Long id, RecetaRequestDTO request);
    void eliminar(Long id);
}