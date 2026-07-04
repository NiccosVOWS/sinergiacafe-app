package pe.com.sinergiacafe.calidad.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.calidad.dto.*;
import pe.com.sinergiacafe.calidad.entity.*;
import pe.com.sinergiacafe.calidad.repository.*;
import pe.com.sinergiacafe.calidad.service.IncidenciaService;
import pe.com.sinergiacafe.calidad.shared.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncidenciaServiceImpl implements IncidenciaService {

    private final IncidenciaRepository repository;
    private final TipoIncidenciaRepository tipoRepository;

    @Override
    public List<IncidenciaResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public IncidenciaResponseDTO obtenerPorId(Long id) {
        IncidenciaEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Incidencia", id));

        return toDTO(entity);
    }

    @Override
    public IncidenciaResponseDTO crear(IncidenciaRequestDTO request) {

        TipoIncidenciaEntity tipo = tipoRepository.findById(request.getIdTipoIncidencia())
                .orElseThrow(() -> new ResourceNotFoundException("TipoIncidencia", request.getIdTipoIncidencia()));

        IncidenciaEntity entity = IncidenciaEntity.builder()
                .tipoIncidencia(tipo)
                .idEmpleado(request.getIdEmpleado())
                .area(request.getArea())
                .descripcion(request.getDescripcion())
                .estado(request.getEstado())
                .build();

        return toDTO(repository.save(entity));
    }

    @Override
    public IncidenciaResponseDTO actualizar(Long id, IncidenciaRequestDTO request) {

        IncidenciaEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Incidencia", id));

        TipoIncidenciaEntity tipo = tipoRepository.findById(request.getIdTipoIncidencia())
                .orElseThrow(() -> new ResourceNotFoundException("TipoIncidencia", request.getIdTipoIncidencia()));

        entity.setTipoIncidencia(tipo);
        entity.setIdEmpleado(request.getIdEmpleado());
        entity.setArea(request.getArea());
        entity.setDescripcion(request.getDescripcion());
        entity.setEstado(request.getEstado());

        return toDTO(repository.save(entity));
    }

    @Override
    public void eliminar(Long id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Incidencia", id);
        }

        repository.deleteById(id);
    }

    private IncidenciaResponseDTO toDTO(IncidenciaEntity entity) {

        return IncidenciaResponseDTO.builder()
                .idIncidencia(entity.getIdIncidencia())
                .tipoIncidencia(TipoIncidenciaSimpleDTO.builder()
                        .idTipoIncidencia(entity.getTipoIncidencia().getIdTipoIncidencia())
                        .nombre(entity.getTipoIncidencia().getNombre())
                        .build())
                .idEmpleado(entity.getIdEmpleado())
                .fechaIncidencia(entity.getFechaIncidencia())
                .area(entity.getArea())
                .descripcion(entity.getDescripcion())
                .estado(entity.getEstado())
                .build();
    }
}