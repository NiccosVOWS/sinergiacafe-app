package pe.com.sinergiacafe.mantenimiento.service;

import pe.com.sinergiacafe.mantenimiento.dto.MantenimientoRequestDTO;
import pe.com.sinergiacafe.mantenimiento.dto.MantenimientoResponseDTO;

import java.util.List;

public interface MantenimientoService {

    List<MantenimientoResponseDTO> listarTodos();
    List<MantenimientoResponseDTO> listarPorEquipo(Long idEquipo);
    MantenimientoResponseDTO obtenerPorId(Long id);
    MantenimientoResponseDTO registrar(MantenimientoRequestDTO request);
    void eliminar(Long id);
}