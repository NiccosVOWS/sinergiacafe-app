package pe.com.sinergiacafe.mantenimiento.service;

import pe.com.sinergiacafe.mantenimiento.dto.TipoMantenimientoRequestDTO;
import pe.com.sinergiacafe.mantenimiento.dto.TipoMantenimientoResponseDTO;

import java.util.List;

public interface TipoMantenimientoService {

    List<TipoMantenimientoResponseDTO> listarTodos();
    TipoMantenimientoResponseDTO obtenerPorId(Long id);
    TipoMantenimientoResponseDTO crear(TipoMantenimientoRequestDTO request);
    TipoMantenimientoResponseDTO actualizar(Long id, TipoMantenimientoRequestDTO request);
    void eliminar(Long id);
}