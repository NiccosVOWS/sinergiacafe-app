package pe.com.sinergiacafe.recursoshumanos.service;

import pe.com.sinergiacafe.recursoshumanos.dto.TurnoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.TurnoResponseDTO;

import java.util.List;

public interface TurnoService {

    List<TurnoResponseDTO> listarTodos();
    TurnoResponseDTO obtenerPorId(Long id);
    TurnoResponseDTO crear(TurnoRequestDTO request);
    TurnoResponseDTO actualizar(Long id, TurnoRequestDTO request);
    void eliminar(Long id);
}