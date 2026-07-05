package pe.com.sinergiacafe.recursoshumanos.service;

import pe.com.sinergiacafe.recursoshumanos.dto.AuditoriaRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.AuditoriaResponseDTO;

import java.util.List;

public interface AuditoriaService {

    List<AuditoriaResponseDTO> listarTodos();
    List<AuditoriaResponseDTO> listarPorUsuario(Long idUsuario);
    AuditoriaResponseDTO obtenerPorId(Long id);
    AuditoriaResponseDTO registrar(AuditoriaRequestDTO request);
}