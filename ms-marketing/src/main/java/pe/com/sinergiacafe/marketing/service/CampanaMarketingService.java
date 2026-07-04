package pe.com.sinergiacafe.marketing.service;

import pe.com.sinergiacafe.marketing.dto.CampanaMarketingRequestDTO;
import pe.com.sinergiacafe.marketing.dto.CampanaMarketingResponseDTO;

import java.util.List;

public interface CampanaMarketingService {

    List<CampanaMarketingResponseDTO> listarTodos();

    CampanaMarketingResponseDTO obtenerPorId(Long id);

    CampanaMarketingResponseDTO crear(CampanaMarketingRequestDTO request);

    CampanaMarketingResponseDTO actualizar(Long id, CampanaMarketingRequestDTO request);

    void eliminar(Long id);
}