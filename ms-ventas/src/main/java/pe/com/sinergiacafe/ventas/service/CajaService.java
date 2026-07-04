package pe.com.sinergiacafe.ventas.service;

import pe.com.sinergiacafe.ventas.dto.CajaRequestDTO;
import pe.com.sinergiacafe.ventas.dto.CajaResponseDTO;

import java.util.List;

public interface CajaService {

    List<CajaResponseDTO> listarTodos();
    CajaResponseDTO obtenerPorId(Long id);
    CajaResponseDTO abrir(CajaRequestDTO request);
    CajaResponseDTO cerrar(Long id);
}