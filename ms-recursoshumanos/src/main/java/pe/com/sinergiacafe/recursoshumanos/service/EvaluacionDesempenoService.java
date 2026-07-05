package pe.com.sinergiacafe.recursoshumanos.service;

import pe.com.sinergiacafe.recursoshumanos.dto.EvaluacionDesempenoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.EvaluacionDesempenoResponseDTO;

import java.util.List;

public interface EvaluacionDesempenoService {

    List<EvaluacionDesempenoResponseDTO> listarTodos();
    List<EvaluacionDesempenoResponseDTO> listarPorEmpleado(Long idEmpleado);
    EvaluacionDesempenoResponseDTO obtenerPorId(Long id);
    EvaluacionDesempenoResponseDTO registrar(EvaluacionDesempenoRequestDTO request);
    void eliminar(Long id);
}