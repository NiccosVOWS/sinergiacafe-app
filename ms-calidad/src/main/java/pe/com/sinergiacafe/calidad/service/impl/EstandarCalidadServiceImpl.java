package pe.com.sinergiacafe.calidad.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.calidad.dto.EstandarCalidadRequestDTO;
import pe.com.sinergiacafe.calidad.dto.EstandarCalidadResponseDTO;
import pe.com.sinergiacafe.calidad.entity.EstandarCalidadEntity;
import pe.com.sinergiacafe.calidad.repository.EstandarCalidadRepository;
import pe.com.sinergiacafe.calidad.service.EstandarCalidadService;
import pe.com.sinergiacafe.calidad.shared.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstandarCalidadServiceImpl implements EstandarCalidadService {

    private final EstandarCalidadRepository repository;

    @Override
    public List<EstandarCalidadResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public EstandarCalidadResponseDTO obtenerPorId(Long id) {
        EstandarCalidadEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EstandarCalidad", id));

        return toDTO(entity);
    }

    @Override
    public EstandarCalidadResponseDTO crear(EstandarCalidadRequestDTO request) {

        EstandarCalidadEntity entity = EstandarCalidadEntity.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .aplicableA(request.getAplicableA())
                .build();

        return toDTO(repository.save(entity));
    }

    @Override
    public EstandarCalidadResponseDTO actualizar(Long id, EstandarCalidadRequestDTO request) {

        EstandarCalidadEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EstandarCalidad", id));

        entity.setNombre(request.getNombre());
        entity.setDescripcion(request.getDescripcion());
        entity.setAplicableA(request.getAplicableA());

        return toDTO(repository.save(entity));
    }

    @Override
    public void eliminar(Long id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("EstandarCalidad", id);
        }

        repository.deleteById(id);
    }

    private EstandarCalidadResponseDTO toDTO(EstandarCalidadEntity entity) {
        return EstandarCalidadResponseDTO.builder()
                .idEstandarCalidad(entity.getIdEstandarCalidad())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .aplicableA(entity.getAplicableA())
                .build();
    }
}