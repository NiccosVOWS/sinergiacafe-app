package pe.com.sinergiacafe.calidad.service;

import pe.com.sinergiacafe.calidad.dto.RespuestaEncuestaRequestDTO;
import pe.com.sinergiacafe.calidad.dto.RespuestaEncuestaResponseDTO;

import java.util.List;

public interface RespuestaEncuestaService {

    List<RespuestaEncuestaResponseDTO> listarTodos();

    RespuestaEncuestaResponseDTO obtenerPorId(Long id);

    RespuestaEncuestaResponseDTO crear(RespuestaEncuestaRequestDTO request);

    RespuestaEncuestaResponseDTO actualizar(Long id, RespuestaEncuestaRequestDTO request);

    void eliminar(Long id);
}