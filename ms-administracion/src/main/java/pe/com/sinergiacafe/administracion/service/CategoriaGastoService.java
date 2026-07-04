package pe.com.sinergiacafe.administracion.service;

import pe.com.sinergiacafe.administracion.dto.CategoriaGastoRequestDTO;
import pe.com.sinergiacafe.administracion.dto.CategoriaGastoResponseDTO;

import java.util.List;

public interface CategoriaGastoService {

    List<CategoriaGastoResponseDTO> listarTodos();

    CategoriaGastoResponseDTO obtenerPorId(Long id);

    CategoriaGastoResponseDTO crear(CategoriaGastoRequestDTO request);

    CategoriaGastoResponseDTO actualizar(Long id, CategoriaGastoRequestDTO request);

    void eliminar(Long id);
}
