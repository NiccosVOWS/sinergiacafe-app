package pe.com.sinergiacafe.calidad.service;

import pe.com.sinergiacafe.calidad.dto.EncuestaSatisfaccionRequestDTO;
import pe.com.sinergiacafe.calidad.dto.EncuestaSatisfaccionResponseDTO;

import java.util.List;

public interface EncuestaSatisfaccionService {

    List<EncuestaSatisfaccionResponseDTO> listarTodos();

    EncuestaSatisfaccionResponseDTO obtenerPorId(Long id);

    EncuestaSatisfaccionResponseDTO crear(EncuestaSatisfaccionRequestDTO request);

    EncuestaSatisfaccionResponseDTO actualizar(Long id, EncuestaSatisfaccionRequestDTO request);

    void eliminar(Long id);
}