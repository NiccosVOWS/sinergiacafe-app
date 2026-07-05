package pe.com.sinergiacafe.mantenimiento.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.mantenimiento.dto.TipoEquipoRequestDTO;
import pe.com.sinergiacafe.mantenimiento.dto.TipoEquipoResponseDTO;
import pe.com.sinergiacafe.mantenimiento.entity.TipoEquipoEntity;
import pe.com.sinergiacafe.mantenimiento.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.mantenimiento.repository.TipoEquipoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoEquipoServiceImpl implements TipoEquipoService {

    private final TipoEquipoRepository tipoEquipoRepository;

    @Override
    public List<TipoEquipoResponseDTO> listarTodos() {
        return tipoEquipoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public TipoEquipoResponseDTO obtenerPorId(Long id) {
        TipoEquipoEntity entity = tipoEquipoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoEquipo", id));
        return toResponseDTO(entity);
    }

    @Override
    public TipoEquipoResponseDTO crear(TipoEquipoRequestDTO request) {
        TipoEquipoEntity entity = TipoEquipoEntity.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .build();
        return toResponseDTO(tipoEquipoRepository.save(entity));
    }

    @Override
    public TipoEquipoResponseDTO actualizar(Long id, TipoEquipoRequestDTO request) {
        TipoEquipoEntity entity = tipoEquipoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoEquipo", id));
        entity.setNombre(request.getNombre());
        entity.setDescripcion(request.getDescripcion());
        return toResponseDTO(tipoEquipoRepository.save(entity));
    }

    @Override
    public void eliminar(Long id) {
        if (!tipoEquipoRepository.existsById(id)) {
            throw new ResourceNotFoundException("TipoEquipo", id);
        }
        tipoEquipoRepository.deleteById(id);
    }

    private TipoEquipoResponseDTO toResponseDTO(TipoEquipoEntity entity) {
        return TipoEquipoResponseDTO.builder()
                .idTipoEquipo(entity.getIdTipoEquipo())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build();
    }
}