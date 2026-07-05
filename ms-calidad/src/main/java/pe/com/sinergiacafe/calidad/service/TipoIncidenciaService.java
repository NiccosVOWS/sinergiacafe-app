package pe.com.sinergiacafe.calidad.service;

import pe.com.sinergiacafe.calidad.dto.TipoIncidenciaRequestDTO;
import pe.com.sinergiacafe.calidad.dto.TipoIncidenciaResponseDTO;

import java.util.List;

public interface TipoIncidenciaService {

    List<TipoIncidenciaResponseDTO> listarTodos();
    TipoIncidenciaResponseDTO obtenerPorId(Long id);
    TipoIncidenciaResponseDTO crear(TipoIncidenciaRequestDTO request);
    TipoIncidenciaResponseDTO actualizar(Long id, TipoIncidenciaRequestDTO request);
    void eliminar(Long id);
}