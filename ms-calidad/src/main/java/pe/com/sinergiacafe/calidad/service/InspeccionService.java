package pe.com.sinergiacafe.calidad.service;

import pe.com.sinergiacafe.calidad.dto.InspeccionRequestDTO;
import pe.com.sinergiacafe.calidad.dto.InspeccionResponseDTO;

import java.util.List;

public interface InspeccionService {

    List<InspeccionResponseDTO> listarTodos();
    List<InspeccionResponseDTO> listarPorArea(String area);
    InspeccionResponseDTO obtenerPorId(Long id);
    InspeccionResponseDTO registrar(InspeccionRequestDTO request);
    InspeccionResponseDTO actualizarResultado(Long id, String resultadoGeneral);
    void eliminar(Long id);
}