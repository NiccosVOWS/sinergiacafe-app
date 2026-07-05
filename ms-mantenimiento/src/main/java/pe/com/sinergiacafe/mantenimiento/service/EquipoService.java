package pe.com.sinergiacafe.mantenimiento.service;

import pe.com.sinergiacafe.mantenimiento.dto.EquipoRequestDTO;
import pe.com.sinergiacafe.mantenimiento.dto.EquipoResponseDTO;

import java.util.List;

public interface EquipoService {

    List<EquipoResponseDTO> listarTodos();
    EquipoResponseDTO obtenerPorId(Long id);
    EquipoResponseDTO crear(EquipoRequestDTO request);
    EquipoResponseDTO actualizar(Long id, EquipoRequestDTO request);
    EquipoResponseDTO actualizarEstado(Long id, String estado);
    void eliminar(Long id);
}