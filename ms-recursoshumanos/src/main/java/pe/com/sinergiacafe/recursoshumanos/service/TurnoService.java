package pe.com.sinergiacafe.recursoshumanos.service;

import pe.com.sinergiacafe.recursoshumanos.dto.TurnoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.TurnoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.entity.TurnoEntity;
import pe.com.sinergiacafe.recursoshumanos.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.recursoshumanos.repository.TurnoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TurnoService {

    private final TurnoRepository repository;

    public List<TurnoResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public TurnoResponseDTO findById(Long id) {
        TurnoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado con id: " + id));
        return toResponse(entity);
    }

    public TurnoResponseDTO create(TurnoRequestDTO dto) {
        TurnoEntity entity = new TurnoEntity();
        entity.setEmpleadoId(dto.getEmpleadoId());
        entity.setFecha(dto.getFecha());
        entity.setHoraEntrada(dto.getHoraEntrada());
        entity.setHoraSalida(dto.getHoraSalida());
        return toResponse(repository.save(entity));
    }

    public TurnoResponseDTO update(Long id, TurnoRequestDTO dto) {
        TurnoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado con id: " + id));
        entity.setEmpleadoId(dto.getEmpleadoId());
        entity.setFecha(dto.getFecha());
        entity.setHoraEntrada(dto.getHoraEntrada());
        entity.setHoraSalida(dto.getHoraSalida());
        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Turno no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }

    private TurnoResponseDTO toResponse(TurnoEntity entity) {
        return TurnoResponseDTO.builder()
                .id(entity.getId())
                .empleadoId(entity.getEmpleadoId())
                .fecha(entity.getFecha())
                .horaEntrada(entity.getHoraEntrada())
                .horaSalida(entity.getHoraSalida())
                .build();
    }
}
