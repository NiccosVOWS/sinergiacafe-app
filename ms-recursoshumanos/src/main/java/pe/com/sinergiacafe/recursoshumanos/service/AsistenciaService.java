package pe.com.sinergiacafe.recursoshumanos.service;

import pe.com.sinergiacafe.recursoshumanos.dto.AsistenciaRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.AsistenciaResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.entity.AsistenciaEntity;
import pe.com.sinergiacafe.recursoshumanos.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.recursoshumanos.repository.AsistenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AsistenciaService {

    private final AsistenciaRepository repository;

    public List<AsistenciaResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public AsistenciaResponseDTO findById(Long id) {
        AsistenciaEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asistencia no encontrado con id: " + id));
        return toResponse(entity);
    }

    public AsistenciaResponseDTO create(AsistenciaRequestDTO dto) {
        AsistenciaEntity entity = new AsistenciaEntity();
        entity.setEmpleadoId(dto.getEmpleadoId());
        entity.setFecha(dto.getFecha());
        entity.setHoraEntrada(dto.getHoraEntrada());
        entity.setHoraSalida(dto.getHoraSalida());
        entity.setObservaciones(dto.getObservaciones());
        return toResponse(repository.save(entity));
    }

    public AsistenciaResponseDTO update(Long id, AsistenciaRequestDTO dto) {
        AsistenciaEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asistencia no encontrado con id: " + id));
        entity.setEmpleadoId(dto.getEmpleadoId());
        entity.setFecha(dto.getFecha());
        entity.setHoraEntrada(dto.getHoraEntrada());
        entity.setHoraSalida(dto.getHoraSalida());
        entity.setObservaciones(dto.getObservaciones());
        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Asistencia no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }

    private AsistenciaResponseDTO toResponse(AsistenciaEntity entity) {
        return AsistenciaResponseDTO.builder()
                .id(entity.getId())
                .empleadoId(entity.getEmpleadoId())
                .fecha(entity.getFecha())
                .horaEntrada(entity.getHoraEntrada())
                .horaSalida(entity.getHoraSalida())
                .observaciones(entity.getObservaciones())
                .build();
    }
}
