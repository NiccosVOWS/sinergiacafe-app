package pe.com.sinergiacafe.calidad.service;

import pe.com.sinergiacafe.calidad.dto.EncuestaSatisfaccionRequestDTO;
import pe.com.sinergiacafe.calidad.dto.EncuestaSatisfaccionResponseDTO;

import java.util.List;

public interface EncuestaSatisfaccionService {

    List<EncuestaSatisfaccionResponseDTO> listarTodos();
    List<EncuestaSatisfaccionResponseDTO> listarPorCliente(Long idCliente);
    EncuestaSatisfaccionResponseDTO obtenerPorId(Long id);
    EncuestaSatisfaccionResponseDTO registrar(EncuestaSatisfaccionRequestDTO request);
    void eliminar(Long id);
}