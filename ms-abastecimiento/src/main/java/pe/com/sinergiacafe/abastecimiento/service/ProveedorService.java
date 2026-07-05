package pe.com.sinergiacafe.abastecimiento.service;

import pe.com.sinergiacafe.abastecimiento.dto.ProveedorRequestDTO;
import pe.com.sinergiacafe.abastecimiento.dto.ProveedorResponseDTO;

import java.util.List;

public interface ProveedorService {

    List<ProveedorResponseDTO> listarTodos();
    ProveedorResponseDTO obtenerPorId(Long id);
    ProveedorResponseDTO crear(ProveedorRequestDTO request);
    ProveedorResponseDTO actualizar(Long id, ProveedorRequestDTO request);
    void eliminar(Long id);
}