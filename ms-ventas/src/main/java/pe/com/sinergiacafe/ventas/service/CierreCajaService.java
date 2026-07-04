package pe.com.sinergiacafe.ventas.service;

import pe.com.sinergiacafe.ventas.dto.CierreCajaRequestDTO;
import pe.com.sinergiacafe.ventas.dto.CierreCajaResponseDTO;

import java.util.List;

public interface CierreCajaService {

    List<CierreCajaResponseDTO> listarPorCaja(Long idCaja);
    CierreCajaResponseDTO registrar(CierreCajaRequestDTO request);
}