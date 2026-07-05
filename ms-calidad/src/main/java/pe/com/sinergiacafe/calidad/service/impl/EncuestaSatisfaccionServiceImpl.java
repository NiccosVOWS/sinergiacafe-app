package pe.com.sinergiacafe.calidad.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.calidad.dto.EncuestaSatisfaccionRequestDTO;
import pe.com.sinergiacafe.calidad.dto.EncuestaSatisfaccionResponseDTO;
import pe.com.sinergiacafe.calidad.entity.EncuestaSatisfaccionEntity;
import pe.com.sinergiacafe.calidad.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.calidad.repository.EncuestaSatisfaccionRepository;
import pe.com.sinergiacafe.calidad.service.EncuestaSatisfaccionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EncuestaSatisfaccionServiceImpl implements EncuestaSatisfaccionService {

    private final EncuestaSatisfaccionRepository encuestaSatisfaccionRepository;

    @Override
    public List<EncuestaSatisfaccionResponseDTO> listarTodos() {
        return encuestaSatisfaccionRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public List<EncuestaSatisfaccionResponseDTO> listarPorCliente(Long idCliente) {
        return encuestaSatisfaccionRepository.findByIdCliente(idCliente)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public EncuestaSatisfaccionResponseDTO obtenerPorId(Long id) {
        EncuestaSatisfaccionEntity entity = encuestaSatisfaccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EncuestaSatisfaccion", id));
        return toResponseDTO(entity);
    }

    @Override
    public EncuestaSatisfaccionResponseDTO registrar(EncuestaSatisfaccionRequestDTO request) {
        EncuestaSatisfaccionEntity entity = EncuestaSatisfaccionEntity.builder()
                .idCliente(request.getIdCliente())
                .canal(request.getCanal())
                .puntuacionGeneral(request.getPuntuacionGeneral())
                .build();
        return toResponseDTO(encuestaSatisfaccionRepository.save(entity));
    }

    @Override
    public void eliminar(Long id) {
        if (!encuestaSatisfaccionRepository.existsById(id)) {
            throw new ResourceNotFoundException("EncuestaSatisfaccion", id);
        }
        encuestaSatisfaccionRepository.deleteById(id);
    }

    public EncuestaSatisfaccionResponseDTO toResponseDTO(EncuestaSatisfaccionEntity entity) {
        return EncuestaSatisfaccionResponseDTO.builder()
                .idEncuestaSatisfaccion(entity.getIdEncuestaSatisfaccion())
                .idCliente(entity.getIdCliente())
                .fechaEncuesta(entity.getFechaEncuesta())
                .canal(entity.getCanal())
                .puntuacionGeneral(entity.getPuntuacionGeneral())
                .build();
    }
}