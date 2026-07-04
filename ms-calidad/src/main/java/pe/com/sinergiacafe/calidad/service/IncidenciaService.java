package pe.com.sinergiacafe.calidad.service;

import pe.com.sinergiacafe.calidad.dto.IncidenciaRequestDTO;
import pe.com.sinergiacafe.calidad.dto.IncidenciaResponseDTO;

import java.util.List;

public interface IncidenciaService {

    List<IncidenciaResponseDTO> listarTodos();

    IncidenciaResponseDTO obtenerPorId(Long id);

    IncidenciaResponseDTO crear(IncidenciaRequestDTO request);

    IncidenciaResponseDTO actualizar(Long id, IncidenciaRequestDTO request);

    void eliminar(Long id);
}