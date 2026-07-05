package pe.com.sinergiacafe.recursoshumanos.service;

import pe.com.sinergiacafe.recursoshumanos.dto.PermisoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.PermisoResponseDTO;

import java.util.List;

public interface PermisoService {

    List<PermisoResponseDTO> listarTodos();
    List<PermisoResponseDTO> listarPorModulo(String modulo);
    PermisoResponseDTO obtenerPorId(Long id);
    PermisoResponseDTO crear(PermisoRequestDTO request);
    PermisoResponseDTO actualizar(Long id, PermisoRequestDTO request);
    void eliminar(Long id);
}