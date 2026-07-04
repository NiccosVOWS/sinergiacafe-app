package pe.com.sinergiacafe.calidad.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.calidad.dto.InspeccionRequestDTO;
import pe.com.sinergiacafe.calidad.dto.InspeccionResponseDTO;
import pe.com.sinergiacafe.calidad.entity.InspeccionEntity;
import pe.com.sinergiacafe.calidad.repository.InspeccionRepository;
import pe.com.sinergiacafe.calidad.service.InspeccionService;
import pe.com.sinergiacafe.calidad.shared.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InspeccionServiceImpl implements InspeccionService {

    private final InspeccionRepository repository;

    @Override
    public List<InspeccionResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public InspeccionResponseDTO obtenerPorId(Long id) {
        InspeccionEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inspeccion", id));

        return toDTO(entity);
    }

    @Override
    public InspeccionResponseDTO crear(InspeccionRequestDTO request) {

        InspeccionEntity entity = InspeccionEntity.builder()
                .idEmpleado(request.getIdEmpleado())
                .areaInspeccionada(request.getAreaInspeccionada())
                .resultadoGeneral(request.getResultadoGeneral())
                .build();

        return toDTO(repository.save(entity));
    }

    @Override
    public InspeccionResponseDTO actualizar(Long id, InspeccionRequestDTO request) {

        InspeccionEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inspeccion", id));

        entity.setIdEmpleado(request.getIdEmpleado());
        entity.setAreaInspeccionada(request.getAreaInspeccionada());
        entity.setResultadoGeneral(request.getResultadoGeneral());

        return toDTO(repository.save(entity));
    }

    @Override
    public void eliminar(Long id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Inspeccion", id);
        }

        repository.deleteById(id);
    }

    private InspeccionResponseDTO toDTO(InspeccionEntity entity) {
        return InspeccionResponseDTO.builder()
                .idInspeccion(entity.getIdInspeccion())
                .idEmpleado(entity.getIdEmpleado())
                .fechaInspeccion(entity.getFechaInspeccion())
                .areaInspeccionada(entity.getAreaInspeccionada())
                .resultadoGeneral(entity.getResultadoGeneral())
                .build();
    }
}