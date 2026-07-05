package pe.com.sinergiacafe.calidad.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.calidad.dto.TipoIncidenciaRequestDTO;
import pe.com.sinergiacafe.calidad.dto.TipoIncidenciaResponseDTO;
import pe.com.sinergiacafe.calidad.entity.TipoIncidenciaEntity;
import pe.com.sinergiacafe.calidad.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.calidad.repository.TipoIncidenciaRepository;
import pe.com.sinergiacafe.calidad.service.TipoIncidenciaService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoIncidenciaServiceImpl implements TipoIncidenciaService {

    private final TipoIncidenciaRepository tipoIncidenciaRepository;

    @Override
    public List<TipoIncidenciaResponseDTO> listarTodos() {
        return tipoIncidenciaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public TipoIncidenciaResponseDTO obtenerPorId(Long id) {
        TipoIncidenciaEntity entity = tipoIncidenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoIncidencia", id));
        return toResponseDTO(entity);
    }

    @Override
    public TipoIncidenciaResponseDTO crear(TipoIncidenciaRequestDTO request) {
        TipoIncidenciaEntity entity = TipoIncidenciaEntity.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .build();
        return toResponseDTO(tipoIncidenciaRepository.save(entity));
    }

    @Override
    public TipoIncidenciaResponseDTO actualizar(Long id, TipoIncidenciaRequestDTO request) {
        TipoIncidenciaEntity entity = tipoIncidenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoIncidencia", id));
        entity.setNombre(request.getNombre());
        entity.setDescripcion(request.getDescripcion());
        return toResponseDTO(tipoIncidenciaRepository.save(entity));
    }

    @Override
    public void eliminar(Long id) {
        if (!tipoIncidenciaRepository.existsById(id)) {
            throw new ResourceNotFoundException("TipoIncidencia", id);
        }
        tipoIncidenciaRepository.deleteById(id);
    }

    public TipoIncidenciaResponseDTO toResponseDTO(TipoIncidenciaEntity entity) {
        return TipoIncidenciaResponseDTO.builder()
                .idTipoIncidencia(entity.getIdTipoIncidencia())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build();
    }
}