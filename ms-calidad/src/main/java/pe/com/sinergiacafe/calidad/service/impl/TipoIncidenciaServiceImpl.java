package pe.com.sinergiacafe.calidad.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.calidad.dto.TipoIncidenciaRequestDTO;
import pe.com.sinergiacafe.calidad.dto.TipoIncidenciaResponseDTO;
import pe.com.sinergiacafe.calidad.entity.TipoIncidenciaEntity;
import pe.com.sinergiacafe.calidad.repository.TipoIncidenciaRepository;
import pe.com.sinergiacafe.calidad.service.TipoIncidenciaService;
import pe.com.sinergiacafe.calidad.shared.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoIncidenciaServiceImpl implements TipoIncidenciaService {

    private final TipoIncidenciaRepository repository;

    @Override
    public List<TipoIncidenciaResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public TipoIncidenciaResponseDTO obtenerPorId(Long id) {
        TipoIncidenciaEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoIncidencia", id));

        return toDTO(entity);
    }

    @Override
    public TipoIncidenciaResponseDTO crear(TipoIncidenciaRequestDTO request) {

        TipoIncidenciaEntity entity = TipoIncidenciaEntity.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .build();

        return toDTO(repository.save(entity));
    }

    @Override
    public TipoIncidenciaResponseDTO actualizar(Long id, TipoIncidenciaRequestDTO request) {

        TipoIncidenciaEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoIncidencia", id));

        entity.setNombre(request.getNombre());
        entity.setDescripcion(request.getDescripcion());

        return toDTO(repository.save(entity));
    }

    @Override
    public void eliminar(Long id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("TipoIncidencia", id);
        }

        repository.deleteById(id);
    }

    private TipoIncidenciaResponseDTO toDTO(TipoIncidenciaEntity entity) {
        return TipoIncidenciaResponseDTO.builder()
                .idTipoIncidencia(entity.getIdTipoIncidencia())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build();
    }
}