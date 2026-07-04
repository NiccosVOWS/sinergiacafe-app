package pe.com.sinergiacafe.calidad.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.calidad.dto.*;
import pe.com.sinergiacafe.calidad.entity.*;
import pe.com.sinergiacafe.calidad.repository.*;
import pe.com.sinergiacafe.calidad.service.RespuestaEncuestaService;
import pe.com.sinergiacafe.calidad.shared.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RespuestaEncuestaServiceImpl implements RespuestaEncuestaService {

    private final RespuestaEncuestaRepository repository;
    private final EncuestaSatisfaccionRepository encuestaRepository;

    @Override
    public List<RespuestaEncuestaResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public RespuestaEncuestaResponseDTO obtenerPorId(Long id) {
        RespuestaEncuestaEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RespuestaEncuesta", id));

        return toDTO(entity);
    }

    @Override
    public RespuestaEncuestaResponseDTO crear(RespuestaEncuestaRequestDTO request) {

        EncuestaSatisfaccionEntity encuesta = encuestaRepository.findById(request.getIdEncuestaSatisfaccion())
                .orElseThrow(() -> new ResourceNotFoundException("EncuestaSatisfaccion", request.getIdEncuestaSatisfaccion()));

        RespuestaEncuestaEntity entity = RespuestaEncuestaEntity.builder()
                .encuestaSatisfaccion(encuesta)
                .pregunta(request.getPregunta())
                .puntuacion(request.getPuntuacion())
                .comentario(request.getComentario())
                .build();

        return toDTO(repository.save(entity));
    }

    @Override
    public RespuestaEncuestaResponseDTO actualizar(Long id, RespuestaEncuestaRequestDTO request) {

        RespuestaEncuestaEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RespuestaEncuesta", id));

        EncuestaSatisfaccionEntity encuesta = encuestaRepository.findById(request.getIdEncuestaSatisfaccion())
                .orElseThrow(() -> new ResourceNotFoundException("EncuestaSatisfaccion", request.getIdEncuestaSatisfaccion()));

        entity.setEncuestaSatisfaccion(encuesta);
        entity.setPregunta(request.getPregunta());
        entity.setPuntuacion(request.getPuntuacion());
        entity.setComentario(request.getComentario());

        return toDTO(repository.save(entity));
    }

    @Override
    public void eliminar(Long id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("RespuestaEncuesta", id);
        }

        repository.deleteById(id);
    }

    private RespuestaEncuestaResponseDTO toDTO(RespuestaEncuestaEntity entity) {

        return RespuestaEncuestaResponseDTO.builder()
                .idRespuestaEncuesta(entity.getIdRespuestaEncuesta())
                .encuestaSatisfaccion(EncuestaSatisfaccionSimpleDTO.builder()
                        .idEncuestaSatisfaccion(entity.getEncuestaSatisfaccion().getIdEncuestaSatisfaccion())
                        .canal(entity.getEncuestaSatisfaccion().getCanal())
                        .puntuacionGeneral(entity.getEncuestaSatisfaccion().getPuntuacionGeneral())
                        .build())
                .pregunta(entity.getPregunta())
                .puntuacion(entity.getPuntuacion())
                .comentario(entity.getComentario())
                .build();
    }
}