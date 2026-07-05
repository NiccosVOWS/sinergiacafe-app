package pe.com.sinergiacafe.mantenimiento.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.mantenimiento.dto.TipoMantenimientoRequestDTO;
import pe.com.sinergiacafe.mantenimiento.dto.TipoMantenimientoResponseDTO;
import pe.com.sinergiacafe.mantenimiento.entity.TipoMantenimientoEntity;
import pe.com.sinergiacafe.mantenimiento.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.mantenimiento.repository.TipoMantenimientoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoMantenimientoServiceImpl implements TipoMantenimientoService {

    private final TipoMantenimientoRepository tipoMantenimientoRepository;

    @Override
    public List<TipoMantenimientoResponseDTO> listarTodos() {
        return tipoMantenimientoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public TipoMantenimientoResponseDTO obtenerPorId(Long id) {
        TipoMantenimientoEntity entity = tipoMantenimientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoMantenimiento", id));
        return toResponseDTO(entity);
    }

    @Override
    public TipoMantenimientoResponseDTO crear(TipoMantenimientoRequestDTO request) {
        TipoMantenimientoEntity entity = TipoMantenimientoEntity.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .build();
        return toResponseDTO(tipoMantenimientoRepository.save(entity));
    }

    @Override
    public TipoMantenimientoResponseDTO actualizar(Long id, TipoMantenimientoRequestDTO request) {
        TipoMantenimientoEntity entity = tipoMantenimientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TipoMantenimiento", id));
        entity.setNombre(request.getNombre());
        entity.setDescripcion(request.getDescripcion());
        return toResponseDTO(tipoMantenimientoRepository.save(entity));
    }

    @Override
    public void eliminar(Long id) {
        if (!tipoMantenimientoRepository.existsById(id)) {
            throw new ResourceNotFoundException("TipoMantenimiento", id);
        }
        tipoMantenimientoRepository.deleteById(id);
    }

    private TipoMantenimientoResponseDTO toResponseDTO(TipoMantenimientoEntity entity) {
        return TipoMantenimientoResponseDTO.builder()
                .idTipoMantenimiento(entity.getIdTipoMantenimiento())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build();
    }
}