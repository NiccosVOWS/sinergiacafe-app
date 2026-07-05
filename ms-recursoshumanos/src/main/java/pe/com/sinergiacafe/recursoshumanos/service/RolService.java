package pe.com.sinergiacafe.recursoshumanos.service;

import pe.com.sinergiacafe.recursoshumanos.dto.RolRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.RolResponseDTO;

import java.util.List;

public interface RolService {

    List<RolResponseDTO> listarTodos();
    List<RolResponseDTO> listarActivos();
    RolResponseDTO obtenerPorId(Long id);
    RolResponseDTO crear(RolRequestDTO request);
    RolResponseDTO actualizar(Long id, RolRequestDTO request);
    RolResponseDTO actualizarEstado(Long id, Boolean activo);
    void eliminar(Long id);
}