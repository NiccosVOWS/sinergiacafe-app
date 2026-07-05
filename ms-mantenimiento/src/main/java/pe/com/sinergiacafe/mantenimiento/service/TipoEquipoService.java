package pe.com.sinergiacafe.mantenimiento.service;

import pe.com.sinergiacafe.mantenimiento.dto.TipoEquipoRequestDTO;
import pe.com.sinergiacafe.mantenimiento.dto.TipoEquipoResponseDTO;

import java.util.List;

public interface TipoEquipoService {

    List<TipoEquipoResponseDTO> listarTodos();
    TipoEquipoResponseDTO obtenerPorId(Long id);
    TipoEquipoResponseDTO crear(TipoEquipoRequestDTO request);
    TipoEquipoResponseDTO actualizar(Long id, TipoEquipoRequestDTO request);
    void eliminar(Long id);
}