package pe.com.sinergiacafe.mantenimiento.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.mantenimiento.dto.EquipoRequestDTO;
import pe.com.sinergiacafe.mantenimiento.dto.EquipoResponseDTO;
import pe.com.sinergiacafe.mantenimiento.dto.TipoEquipoResponseDTO;
import pe.com.sinergiacafe.mantenimiento.entity.EquipoEntity;
import pe.com.sinergiacafe.mantenimiento.entity.TipoEquipoEntity;
import pe.com.sinergiacafe.mantenimiento.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.mantenimiento.repository.EquipoRepository;
import pe.com.sinergiacafe.mantenimiento.repository.TipoEquipoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipoServiceImpl implements EquipoService {

    private final EquipoRepository equipoRepository;
    private final TipoEquipoRepository tipoEquipoRepository;

    @Override
    public List<EquipoResponseDTO> listarTodos() {
        return equipoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public EquipoResponseDTO obtenerPorId(Long id) {
        EquipoEntity equipo = equipoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo", id));
        return toResponseDTO(equipo);
    }

    @Override
    public EquipoResponseDTO crear(EquipoRequestDTO request) {
        TipoEquipoEntity tipoEquipo = tipoEquipoRepository.findById(request.getIdTipoEquipo())
                .orElseThrow(() -> new ResourceNotFoundException("TipoEquipo", request.getIdTipoEquipo()));
        EquipoEntity equipo = EquipoEntity.builder()
                .tipoEquipo(tipoEquipo)
                .nombre(request.getNombre())
                .modelo(request.getModelo())
                .fechaAdquisicion(request.getFechaAdquisicion())
                .build();
        return toResponseDTO(equipoRepository.save(equipo));
    }

    @Override
    public EquipoResponseDTO actualizar(Long id, EquipoRequestDTO request) {
        EquipoEntity equipo = equipoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo", id));
        TipoEquipoEntity tipoEquipo = tipoEquipoRepository.findById(request.getIdTipoEquipo())
                .orElseThrow(() -> new ResourceNotFoundException("TipoEquipo", request.getIdTipoEquipo()));
        equipo.setTipoEquipo(tipoEquipo);
        equipo.setNombre(request.getNombre());
        equipo.setModelo(request.getModelo());
        equipo.setFechaAdquisicion(request.getFechaAdquisicion());
        return toResponseDTO(equipoRepository.save(equipo));
    }

    @Override
    public EquipoResponseDTO actualizarEstado(Long id, String estado) {
        EquipoEntity equipo = equipoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo", id));
        equipo.setEstado(EquipoEntity.EstadoEquipo.valueOf(estado));
        return toResponseDTO(equipoRepository.save(equipo));
    }

    @Override
    public void eliminar(Long id) {
        if (!equipoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Equipo", id);
        }
        equipoRepository.deleteById(id);
    }

    private EquipoResponseDTO toResponseDTO(EquipoEntity entity) {
        return EquipoResponseDTO.builder()
                .idEquipo(entity.getIdEquipo())
                .tipoEquipo(TipoEquipoResponseDTO.builder()
                        .idTipoEquipo(entity.getTipoEquipo().getIdTipoEquipo())
                        .nombre(entity.getTipoEquipo().getNombre())
                        .descripcion(entity.getTipoEquipo().getDescripcion())
                        .build())
                .nombre(entity.getNombre())
                .modelo(entity.getModelo())
                .fechaAdquisicion(entity.getFechaAdquisicion())
                .estado(entity.getEstado().name())
                .build();
    }
}