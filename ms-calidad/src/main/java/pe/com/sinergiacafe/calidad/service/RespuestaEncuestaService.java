package pe.com.sinergiacafe.calidad.service;

import pe.com.sinergiacafe.calidad.dto.RespuestaEncuestaRequestDTO;
import pe.com.sinergiacafe.calidad.dto.RespuestaEncuestaResponseDTO;

import java.util.List;

public interface RespuestaEncuestaService {

    List<RespuestaEncuestaResponseDTO> listarTodos();
    List<RespuestaEncuestaResponseDTO> listarPorEncuesta(Long idEncuestaSatisfaccion);
    RespuestaEncuestaResponseDTO obtenerPorId(Long id);
    RespuestaEncuestaResponseDTO registrar(RespuestaEncuestaRequestDTO request);
    void eliminar(Long id);
}