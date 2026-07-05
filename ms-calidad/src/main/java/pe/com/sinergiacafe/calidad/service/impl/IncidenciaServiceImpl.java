package pe.com.sinergiacafe.calidad.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.calidad.dto.IncidenciaRequestDTO;
import pe.com.sinergiacafe.calidad.dto.IncidenciaResponseDTO;
import pe.com.sinergiacafe.calidad.dto.TipoIncidenciaResponseDTO;
import pe.com.sinergiacafe.calidad.entity.IncidenciaEntity;
import pe.com.sinergiacafe.calidad.entity.TipoIncidenciaEntity;
import pe.com.sinergiacafe.calidad.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.calidad.repository.IncidenciaRepository;
import pe.com.sinergiacafe.calidad.repository.TipoIncidenciaRepository;
import pe.com.sinergiacafe.calidad.service.IncidenciaService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncidenciaServiceImpl implements IncidenciaService {

    private final IncidenciaRepository incidenciaRepository;
    private final TipoIncidenciaRepository tipoIncidenciaRepository;

    @Override
    public List<IncidenciaResponseDTO> listarTodos() {
        return incidenciaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public List<IncidenciaResponseDTO> listarPorEstado(String estado) {
        return incidenciaRepository
                .findByEstado(IncidenciaEntity.EstadoIncidencia.valueOf(estado))
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public IncidenciaResponseDTO obtenerPorId(Long id) {
        IncidenciaEntity entity = incidenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Incidencia", id));
        return toResponseDTO(entity);
    }

    @Override
    public IncidenciaResponseDTO registrar(IncidenciaRequestDTO request) {
        TipoIncidenciaEntity tipo = tipoIncidenciaRepository.findById(request.getIdTipoIncidencia())
                .orElseThrow(() -> new ResourceNotFoundException("TipoIncidencia", request.getIdTipoIncidencia()));

        IncidenciaEntity entity = IncidenciaEntity.builder()
                .tipoIncidencia(tipo)
                .idEmpleado(request.getIdEmpleado())
                .area(request.getArea())
                .descripcion(request.getDescripcion())
                .build();

        return toResponseDTO(incidenciaRepository.save(entity));
    }

    @Override
    public IncidenciaResponseDTO actualizarEstado(Long id, String estado) {
        IncidenciaEntity entity = incidenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Incidencia", id));
        entity.setEstado(IncidenciaEntity.EstadoIncidencia.valueOf(estado));
        return toResponseDTO(incidenciaRepository.save(entity));
    }

    @Override
    public void eliminar(Long id) {
        if (!incidenciaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Incidencia", id);
        }
        incidenciaRepository.deleteById(id);
    }

    private IncidenciaResponseDTO toResponseDTO(IncidenciaEntity entity) {
        return IncidenciaResponseDTO.builder()
                .idIncidencia(entity.getIdIncidencia())
                .tipoIncidencia(TipoIncidenciaResponseDTO.builder()
                        .idTipoIncidencia(entity.getTipoIncidencia().getIdTipoIncidencia())
                        .nombre(entity.getTipoIncidencia().getNombre())
                        .descripcion(entity.getTipoIncidencia().getDescripcion())
                        .build())
                .idEmpleado(entity.getIdEmpleado())
                .fechaIncidencia(entity.getFechaIncidencia())
                .area(entity.getArea())
                .descripcion(entity.getDescripcion())
                .estado(entity.getEstado().name())
                .build();
    }
}