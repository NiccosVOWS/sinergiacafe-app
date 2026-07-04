package pe.com.sinergiacafe.clientes.service;

import pe.com.sinergiacafe.clientes.dto.ProgramaFidelizacionResponseDTO;

import java.util.List;

public interface ProgramaFidelizacionService {

    List<ProgramaFidelizacionResponseDTO> listarTodos();
    List<ProgramaFidelizacionResponseDTO> listarActivos();
    ProgramaFidelizacionResponseDTO obtenerPorId(Long id);
}