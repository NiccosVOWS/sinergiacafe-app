package pe.com.sinergiacafe.calidad.service;

import pe.com.sinergiacafe.calidad.dto.InspeccionRequestDTO;
import pe.com.sinergiacafe.calidad.dto.InspeccionResponseDTO;

import java.util.List;

public interface InspeccionService {

    List<InspeccionResponseDTO> listarTodos();

    InspeccionResponseDTO obtenerPorId(Long id);

    InspeccionResponseDTO crear(InspeccionRequestDTO request);

    InspeccionResponseDTO actualizar(Long id, InspeccionRequestDTO request);

    void eliminar(Long id);
}