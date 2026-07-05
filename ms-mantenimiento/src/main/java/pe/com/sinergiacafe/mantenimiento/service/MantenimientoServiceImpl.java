package pe.com.sinergiacafe.mantenimiento.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.mantenimiento.dto.EquipoResponseDTO;
import pe.com.sinergiacafe.mantenimiento.dto.MantenimientoRequestDTO;
import pe.com.sinergiacafe.mantenimiento.dto.MantenimientoResponseDTO;
import pe.com.sinergiacafe.mantenimiento.dto.TipoEquipoResponseDTO;
import pe.com.sinergiacafe.mantenimiento.dto.TipoMantenimientoResponseDTO;
import pe.com.sinergiacafe.mantenimiento.entity.EquipoEntity;
import pe.com.sinergiacafe.mantenimiento.entity.MantenimientoEntity;
import pe.com.sinergiacafe.mantenimiento.entity.TipoMantenimientoEntity;
import pe.com.sinergiacafe.mantenimiento.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.mantenimiento.repository.EquipoRepository;
import pe.com.sinergiacafe.mantenimiento.repository.MantenimientoRepository;
import pe.com.sinergiacafe.mantenimiento.repository.TipoMantenimientoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MantenimientoServiceImpl implements MantenimientoService {

    private final MantenimientoRepository mantenimientoRepository;
    private final EquipoRepository equipoRepository;
    private final TipoMantenimientoRepository tipoMantenimientoRepository;

    @Override
    public List<MantenimientoResponseDTO> listarTodos() {
        return mantenimientoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public List<MantenimientoResponseDTO> listarPorEquipo(Long idEquipo) {
        return mantenimientoRepository.findByEquipo_IdEquipo(idEquipo)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public MantenimientoResponseDTO obtenerPorId(Long id) {
        MantenimientoEntity mantenimiento = mantenimientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mantenimiento", id));
        return toResponseDTO(mantenimiento);
    }

    @Override
    public MantenimientoResponseDTO registrar(MantenimientoRequestDTO request) {
        EquipoEntity equipo = equipoRepository.findById(request.getIdEquipo())
                .orElseThrow(() -> new ResourceNotFoundException("Equipo", request.getIdEquipo()));
        TipoMantenimientoEntity tipoMantenimiento = tipoMantenimientoRepository
                .findById(request.getIdTipoMantenimiento())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "TipoMantenimiento", request.getIdTipoMantenimiento()));

        MantenimientoEntity mantenimiento = MantenimientoEntity.builder()
                .equipo(equipo)
                .tipoMantenimiento(tipoMantenimiento)
                .idEmpleado(request.getIdEmpleado())
                .costo(request.getCosto())
                .observaciones(request.getObservaciones())
                .build();

        return toResponseDTO(mantenimientoRepository.save(mantenimiento));
    }

    @Override
    public void eliminar(Long id) {
        if (!mantenimientoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Mantenimiento", id);
        }
        mantenimientoRepository.deleteById(id);
    }

    private MantenimientoResponseDTO toResponseDTO(MantenimientoEntity entity) {
        return MantenimientoResponseDTO.builder()
                .idMantenimiento(entity.getIdMantenimiento())
                .equipo(EquipoResponseDTO.builder()
                        .idEquipo(entity.getEquipo().getIdEquipo())
                        .tipoEquipo(TipoEquipoResponseDTO.builder()
                                .idTipoEquipo(entity.getEquipo().getTipoEquipo().getIdTipoEquipo())
                                .nombre(entity.getEquipo().getTipoEquipo().getNombre())
                                .descripcion(entity.getEquipo().getTipoEquipo().getDescripcion())
                                .build())
                        .nombre(entity.getEquipo().getNombre())
                        .modelo(entity.getEquipo().getModelo())
                        .fechaAdquisicion(entity.getEquipo().getFechaAdquisicion())
                        .estado(entity.getEquipo().getEstado().name())
                        .build())
                .tipoMantenimiento(TipoMantenimientoResponseDTO.builder()
                        .idTipoMantenimiento(entity.getTipoMantenimiento().getIdTipoMantenimiento())
                        .nombre(entity.getTipoMantenimiento().getNombre())
                        .descripcion(entity.getTipoMantenimiento().getDescripcion())
                        .build())
                .idEmpleado(entity.getIdEmpleado())
                .fechaMantenimiento(entity.getFechaMantenimiento())
                .costo(entity.getCosto())
                .observaciones(entity.getObservaciones())
                .build();
    }
}