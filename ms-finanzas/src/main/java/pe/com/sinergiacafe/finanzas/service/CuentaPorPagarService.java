package pe.com.sinergiacafe.finanzas.service;

import pe.com.sinergiacafe.finanzas.dto.CuentaPorPagarRequestDTO;
import pe.com.sinergiacafe.finanzas.dto.CuentaPorPagarResponseDTO;

import java.util.List;

public interface CuentaPorPagarService {

    List<CuentaPorPagarResponseDTO> listarTodos();
    List<CuentaPorPagarResponseDTO> listarPorProveedor(Long idProveedor);
    CuentaPorPagarResponseDTO obtenerPorId(Long id);
    CuentaPorPagarResponseDTO crear(CuentaPorPagarRequestDTO request);
    CuentaPorPagarResponseDTO actualizarEstado(Long id, String estado);
    void eliminar(Long id);
}