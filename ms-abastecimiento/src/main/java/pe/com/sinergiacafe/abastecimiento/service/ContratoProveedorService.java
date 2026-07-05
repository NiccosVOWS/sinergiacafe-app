package pe.com.sinergiacafe.abastecimiento.service;

import pe.com.sinergiacafe.abastecimiento.dto.ContratoProveedorRequestDTO;
import pe.com.sinergiacafe.abastecimiento.dto.ContratoProveedorResponseDTO;

import java.util.List;

public interface ContratoProveedorService {

    List<ContratoProveedorResponseDTO> listarTodos();
    List<ContratoProveedorResponseDTO> listarPorProveedor(Long idProveedor);
    ContratoProveedorResponseDTO obtenerPorId(Long id);
    ContratoProveedorResponseDTO crear(ContratoProveedorRequestDTO request);
    ContratoProveedorResponseDTO actualizar(Long id, ContratoProveedorRequestDTO request);
    void eliminar(Long id);
}