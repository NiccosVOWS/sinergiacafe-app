package pe.com.sinergiacafe.calidad.service;

import pe.com.sinergiacafe.calidad.dto.IncidenciaRequestDTO;
import pe.com.sinergiacafe.calidad.dto.IncidenciaResponseDTO;

import java.util.List;

public interface IncidenciaService {

    List<IncidenciaResponseDTO> listarTodos();
    List<IncidenciaResponseDTO> listarPorEstado(String estado);
    IncidenciaResponseDTO obtenerPorId(Long id);
    IncidenciaResponseDTO registrar(IncidenciaRequestDTO request);
    IncidenciaResponseDTO actualizarEstado(Long id, String estado);
    void eliminar(Long id);
}