package pe.com.sinergiacafe.recursoshumanos.service;

import pe.com.sinergiacafe.recursoshumanos.dto.RolPermisoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.RolPermisoResponseDTO;

import java.util.List;

public interface RolPermisoService {

    List<RolPermisoResponseDTO> listarTodos();
    List<RolPermisoResponseDTO> listarPorRol(Long idRol);
    RolPermisoResponseDTO asignar(RolPermisoRequestDTO request);
    void eliminar(Long idRol, Long idPermiso);
}