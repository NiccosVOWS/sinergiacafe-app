package pe.com.sinergiacafe.calidad.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.calidad.dto.RespuestaEncuestaRequestDTO;
import pe.com.sinergiacafe.calidad.dto.RespuestaEncuestaResponseDTO;
import pe.com.sinergiacafe.calidad.entity.EncuestaSatisfaccionEntity;
import pe.com.sinergiacafe.calidad.entity.RespuestaEncuestaEntity;
import pe.com.sinergiacafe.calidad.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.calidad.repository.EncuestaSatisfaccionRepository;
import pe.com.sinergiacafe.calidad.repository.RespuestaEncuestaRepository;
import pe.com.sinergiacafe.calidad.service.RespuestaEncuestaService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RespuestaEncuestaServiceImpl implements RespuestaEncuestaService {

    private final RespuestaEncuestaRepository respuestaEncuestaRepository;
    private final EncuestaSatisfaccionRepository encuestaSatisfaccionRepository;
    private final EncuestaSatisfaccionServiceImpl encuestaSatisfaccionService;

    @Override
    public List<RespuestaEncuestaResponseDTO> listarTodos() {
        return respuestaEncuestaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public List<RespuestaEncuestaResponseDTO> listarPorEncuesta(Long idEncuestaSatisfaccion) {
        return respuestaEncuestaRepository
                .findByEncuestaSatisfaccion_IdEncuestaSatisfaccion(idEncuestaSatisfaccion)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public RespuestaEncuestaResponseDTO obtenerPorId(Long id) {
        RespuestaEncuestaEntity entity = respuestaEncuestaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RespuestaEncuesta", id));
        return toResponseDTO(entity);
    }

    @Override
    public RespuestaEncuestaResponseDTO registrar(RespuestaEncuestaRequestDTO request) {
        EncuestaSatisfaccionEntity encuesta = encuestaSatisfaccionRepository
                .findById(request.getIdEncuestaSatisfaccion())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "EncuestaSatisfaccion", request.getIdEncuestaSatisfaccion()));

        RespuestaEncuestaEntity entity = RespuestaEncuestaEntity.builder()
                .encuestaSatisfaccion(encuesta)
                .pregunta(request.getPregunta())
                .puntuacion(request.getPuntuacion())
                .comentario(request.getComentario())
                .build();

        return toResponseDTO(respuestaEncuestaRepository.save(entity));
    }

    @Override
    public void eliminar(Long id) {
        if (!respuestaEncuestaRepository.existsById(id)) {
            throw new ResourceNotFoundException("RespuestaEncuesta", id);
        }
        respuestaEncuestaRepository.deleteById(id);
    }

    private RespuestaEncuestaResponseDTO toResponseDTO(RespuestaEncuestaEntity entity) {
        return RespuestaEncuestaResponseDTO.builder()
                .idRespuestaEncuesta(entity.getIdRespuestaEncuesta())
                .encuestaSatisfaccion(
                        encuestaSatisfaccionService.toResponseDTO(entity.getEncuestaSatisfaccion()))
                .pregunta(entity.getPregunta())
                .puntuacion(entity.getPuntuacion())
                .comentario(entity.getComentario())
                .build();
    }
}