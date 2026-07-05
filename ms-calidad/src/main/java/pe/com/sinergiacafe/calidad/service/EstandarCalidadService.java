package pe.com.sinergiacafe.calidad.service;

import pe.com.sinergiacafe.calidad.dto.EstandarCalidadRequestDTO;
import pe.com.sinergiacafe.calidad.dto.EstandarCalidadResponseDTO;

import java.util.List;

public interface EstandarCalidadService {

    List<EstandarCalidadResponseDTO> listarTodos();
    List<EstandarCalidadResponseDTO> listarPorAplicableA(String aplicableA);
    EstandarCalidadResponseDTO obtenerPorId(Long id);
    EstandarCalidadResponseDTO crear(EstandarCalidadRequestDTO request);
    EstandarCalidadResponseDTO actualizar(Long id, EstandarCalidadRequestDTO request);
    void eliminar(Long id);
}