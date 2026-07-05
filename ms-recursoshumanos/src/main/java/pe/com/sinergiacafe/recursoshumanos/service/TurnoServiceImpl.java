package pe.com.sinergiacafe.recursoshumanos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.recursoshumanos.dto.TurnoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.TurnoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.entity.TurnoEntity;
import pe.com.sinergiacafe.recursoshumanos.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.recursoshumanos.repository.TurnoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TurnoServiceImpl implements TurnoService {

    private final TurnoRepository turnoRepository;

    @Override
    public List<TurnoResponseDTO> listarTodos() {
        return turnoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public TurnoResponseDTO obtenerPorId(Long id) {
        TurnoEntity turno = turnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turno", id));
        return toResponseDTO(turno);
    }

    @Override
    public TurnoResponseDTO crear(TurnoRequestDTO request) {
        TurnoEntity turno = TurnoEntity.builder()
                .nombre(request.getNombre())
                .horaInicio(request.getHoraInicio())
                .horaFin(request.getHoraFin())
                .build();
        return toResponseDTO(turnoRepository.save(turno));
    }

    @Override
    public TurnoResponseDTO actualizar(Long id, TurnoRequestDTO request) {
        TurnoEntity turno = turnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turno", id));
        turno.setNombre(request.getNombre());
        turno.setHoraInicio(request.getHoraInicio());
        turno.setHoraFin(request.getHoraFin());
        return toResponseDTO(turnoRepository.save(turno));
    }

    @Override
    public void eliminar(Long id) {
        if (!turnoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Turno", id);
        }
        turnoRepository.deleteById(id);
    }

    public TurnoResponseDTO toResponseDTO(TurnoEntity entity) {
        return TurnoResponseDTO.builder()
                .idTurno(entity.getIdTurno())
                .nombre(entity.getNombre())
                .horaInicio(entity.getHoraInicio())
                .horaFin(entity.getHoraFin())
                .build();
    }
}