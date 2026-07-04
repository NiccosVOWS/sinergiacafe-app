package pe.com.sinergiacafe.administracion.service;

import pe.com.sinergiacafe.administracion.dto.GastoRequestDTO;
import pe.com.sinergiacafe.administracion.dto.GastoResponseDTO;

import java.util.List;

public interface GastoService {

    List<GastoResponseDTO> listarTodos();

    GastoResponseDTO obtenerPorId(Long id);

    GastoResponseDTO crear(GastoRequestDTO request);

    GastoResponseDTO actualizar(Long id, GastoRequestDTO request);

    void eliminar(Long id);
}
