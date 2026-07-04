package pe.com.sinergiacafe.finanzas.service;

import pe.com.sinergiacafe.finanzas.dto.ReporteFinancieroRequestDTO;
import pe.com.sinergiacafe.finanzas.dto.ReporteFinancieroResponseDTO;

import java.util.List;

public interface ReporteFinancieroService {

    List<ReporteFinancieroResponseDTO> listarTodos();

    ReporteFinancieroResponseDTO obtenerPorId(Long id);

    ReporteFinancieroResponseDTO crear(ReporteFinancieroRequestDTO request);

    ReporteFinancieroResponseDTO actualizar(Long id, ReporteFinancieroRequestDTO request);

    void eliminar(Long id);

}