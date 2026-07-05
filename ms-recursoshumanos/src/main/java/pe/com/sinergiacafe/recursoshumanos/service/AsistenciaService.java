package pe.com.sinergiacafe.recursoshumanos.service;

import pe.com.sinergiacafe.recursoshumanos.dto.AsistenciaRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.AsistenciaResponseDTO;

import java.util.List;

public interface AsistenciaService {

    List<AsistenciaResponseDTO> listarTodos();
    List<AsistenciaResponseDTO> listarPorEmpleado(Long idEmpleado);
    AsistenciaResponseDTO obtenerPorId(Long id);
    AsistenciaResponseDTO registrar(AsistenciaRequestDTO request);
    AsistenciaResponseDTO actualizarHoraSalida(Long id, java.time.LocalTime horaSalida);
    void eliminar(Long id);
}