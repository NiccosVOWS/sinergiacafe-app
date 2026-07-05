package pe.com.sinergiacafe.calidad.service;

import pe.com.sinergiacafe.calidad.dto.ResultadoInspeccionRequestDTO;
import pe.com.sinergiacafe.calidad.dto.ResultadoInspeccionResponseDTO;

import java.util.List;

public interface ResultadoInspeccionService {

    List<ResultadoInspeccionResponseDTO> listarTodos();
    List<ResultadoInspeccionResponseDTO> listarPorInspeccion(Long idInspeccion);
    ResultadoInspeccionResponseDTO obtenerPorId(Long id);
    ResultadoInspeccionResponseDTO registrar(ResultadoInspeccionRequestDTO request);
    void eliminar(Long id);
}