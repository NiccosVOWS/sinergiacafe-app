package pe.com.sinergiacafe.marketing.service;

import pe.com.sinergiacafe.marketing.dto.PromocionRequestDTO;
import pe.com.sinergiacafe.marketing.dto.PromocionResponseDTO;

import java.util.List;

public interface PromocionService {

    List<PromocionResponseDTO> listarTodos();

    PromocionResponseDTO obtenerPorId(Long id);

    PromocionResponseDTO crear(PromocionRequestDTO request);

    PromocionResponseDTO actualizar(Long id, PromocionRequestDTO request);

    void eliminar(Long id);
}