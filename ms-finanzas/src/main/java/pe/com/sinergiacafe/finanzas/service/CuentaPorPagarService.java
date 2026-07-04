package pe.com.sinergiacafe.finanzas.service;

import pe.com.sinergiacafe.finanzas.dto.CuentaPorPagarRequestDTO;
import pe.com.sinergiacafe.finanzas.dto.CuentaPorPagarResponseDTO;

import java.util.List;

public interface CuentaPorPagarService {

    List<CuentaPorPagarResponseDTO> listarTodos();

    CuentaPorPagarResponseDTO obtenerPorId(Long id);

    CuentaPorPagarResponseDTO crear(CuentaPorPagarRequestDTO request);

    CuentaPorPagarResponseDTO actualizar(Long id, CuentaPorPagarRequestDTO request);

    void eliminar(Long id);

}
