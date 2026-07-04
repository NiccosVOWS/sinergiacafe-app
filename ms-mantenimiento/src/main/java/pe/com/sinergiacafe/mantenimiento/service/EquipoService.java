package pe.com.sinergiacafe.mantenimiento.service;

import pe.com.sinergiacafe.mantenimiento.dto.EquipoRequestDTO;
import pe.com.sinergiacafe.mantenimiento.dto.EquipoResponseDTO;
import pe.com.sinergiacafe.mantenimiento.entity.EquipoEntity;
import pe.com.sinergiacafe.mantenimiento.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.mantenimiento.repository.EquipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipoService {

    private final EquipoRepository repository;

    public List<EquipoResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public EquipoResponseDTO findById(Long id) {
        EquipoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado con id: " + id));
        return toResponse(entity);
    }

    public EquipoResponseDTO create(EquipoRequestDTO dto) {
        EquipoEntity entity = new EquipoEntity();
        entity.setNombre(dto.getNombre());
        entity.setModelo(dto.getModelo());
        entity.setFechaAdquisicion(dto.getFechaAdquisicion());
        entity.setEstado(dto.getEstado());
        entity.setTipoEquipoId(dto.getTipoEquipoId());
        return toResponse(repository.save(entity));
    }

    public EquipoResponseDTO update(Long id, EquipoRequestDTO dto) {
        EquipoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado con id: " + id));
        entity.setNombre(dto.getNombre());
        entity.setModelo(dto.getModelo());
        entity.setFechaAdquisicion(dto.getFechaAdquisicion());
        entity.setEstado(dto.getEstado());
        entity.setTipoEquipoId(dto.getTipoEquipoId());
        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Equipo no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }

    private EquipoResponseDTO toResponse(EquipoEntity entity) {
        return EquipoResponseDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .modelo(entity.getModelo())
                .fechaAdquisicion(entity.getFechaAdquisicion())
                .estado(entity.getEstado())
                .tipoEquipoId(entity.getTipoEquipoId())
                .build();
    }
}
