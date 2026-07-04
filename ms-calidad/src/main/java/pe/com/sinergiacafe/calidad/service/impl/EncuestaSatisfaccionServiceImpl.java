package pe.com.sinergiacafe.calidad.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.calidad.dto.*;
import pe.com.sinergiacafe.calidad.entity.*;
import pe.com.sinergiacafe.calidad.repository.*;
import pe.com.sinergiacafe.calidad.service.EncuestaSatisfaccionService;
import pe.com.sinergiacafe.calidad.shared.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EncuestaSatisfaccionServiceImpl implements EncuestaSatisfaccionService {

    private final EncuestaSatisfaccionRepository repository;

    @Override
    public List<EncuestaSatisfaccionResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public EncuestaSatisfaccionResponseDTO obtenerPorId(Long id) {
        EncuestaSatisfaccionEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EncuestaSatisfaccion", id));

        return toDTO(entity);
    }

    @Override
    public EncuestaSatisfaccionResponseDTO crear(EncuestaSatisfaccionRequestDTO request) {

        EncuestaSatisfaccionEntity entity = EncuestaSatisfaccionEntity.builder()
                .idCliente(request.getIdCliente())
                .canal(request.getCanal())
                .puntuacionGeneral(request.getPuntuacionGeneral())
                .build();

        return toDTO(repository.save(entity));
    }

    @Override
    public EncuestaSatisfaccionResponseDTO actualizar(Long id, EncuestaSatisfaccionRequestDTO request) {

        EncuestaSatisfaccionEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EncuestaSatisfaccion", id));

        entity.setIdCliente(request.getIdCliente());
        entity.setCanal(request.getCanal());
        entity.setPuntuacionGeneral(request.getPuntuacionGeneral());

        return toDTO(repository.save(entity));
    }

    @Override
    public void eliminar(Long id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("EncuestaSatisfaccion", id);
        }

        repository.deleteById(id);
    }

    private EncuestaSatisfaccionResponseDTO toDTO(EncuestaSatisfaccionEntity entity) {

        return EncuestaSatisfaccionResponseDTO.builder()
                .idEncuestaSatisfaccion(entity.getIdEncuestaSatisfaccion())
                .idCliente(entity.getIdCliente())
                .fechaEncuesta(entity.getFechaEncuesta())
                .canal(entity.getCanal())
                .puntuacionGeneral(entity.getPuntuacionGeneral())
                .build();
    }
}