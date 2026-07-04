package pe.com.sinergiacafe.calidad.service;

import pe.com.sinergiacafe.calidad.dto.ResultadoInspeccionRequestDTO;
import pe.com.sinergiacafe.calidad.dto.ResultadoInspeccionResponseDTO;

import java.util.List;

public interface ResultadoInspeccionService {

    List<ResultadoInspeccionResponseDTO> listarTodos();

    ResultadoInspeccionResponseDTO obtenerPorId(Long id);

    ResultadoInspeccionResponseDTO crear(ResultadoInspeccionRequestDTO request);

    ResultadoInspeccionResponseDTO actualizar(Long id, ResultadoInspeccionRequestDTO request);

    void eliminar(Long id);
}