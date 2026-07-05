package pe.com.sinergiacafe.recursoshumanos.service;

import pe.com.sinergiacafe.recursoshumanos.dto.EmpleadoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.EmpleadoResponseDTO;

import java.util.List;

public interface EmpleadoService {

    List<EmpleadoResponseDTO> listarTodos();
    EmpleadoResponseDTO obtenerPorId(Long id);
    EmpleadoResponseDTO obtenerPorDni(String dni);
    EmpleadoResponseDTO crear(EmpleadoRequestDTO request);
    EmpleadoResponseDTO actualizar(Long id, EmpleadoRequestDTO request);
    EmpleadoResponseDTO actualizarEstadoLaboral(Long id, String estadoLaboral);
    void eliminar(Long id);
}