package pe.com.sinergiacafe.recursoshumanos.service;

import pe.com.sinergiacafe.recursoshumanos.dto.EmpleadoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.EmpleadoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.entity.EmpleadoEntity;
import pe.com.sinergiacafe.recursoshumanos.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.recursoshumanos.repository.EmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpleadoService {

    private final EmpleadoRepository repository;

    public List<EmpleadoResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public EmpleadoResponseDTO findById(Long id) {
        EmpleadoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + id));
        return toResponse(entity);
    }

    public EmpleadoResponseDTO create(EmpleadoRequestDTO dto) {
        EmpleadoEntity entity = new EmpleadoEntity();
        entity.setIdCargo(dto.getIdCargo());
        entity.setIdTurno(dto.getIdTurno());
        entity.setNombres(dto.getNombres());
        entity.setApellidos(dto.getApellidos());
        entity.setDni(dto.getDni());
        entity.setTelefono(dto.getTelefono());
        entity.setCorreo(dto.getCorreo());
        entity.setFechaIngreso(dto.getFechaIngreso());
        entity.setEstadoLaboral(dto.getEstadoLaboral());
        return toResponse(repository.save(entity));
    }

    public EmpleadoResponseDTO update(Long id, EmpleadoRequestDTO dto) {
        EmpleadoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + id));
        entity.setIdCargo(dto.getIdCargo());
        entity.setIdTurno(dto.getIdTurno());
        entity.setNombres(dto.getNombres());
        entity.setApellidos(dto.getApellidos());
        entity.setDni(dto.getDni());
        entity.setTelefono(dto.getTelefono());
        entity.setCorreo(dto.getCorreo());
        entity.setFechaIngreso(dto.getFechaIngreso());
        entity.setEstadoLaboral(dto.getEstadoLaboral());
        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Empleado no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }

    private EmpleadoResponseDTO toResponse(EmpleadoEntity entity) {
        return EmpleadoResponseDTO.builder()
                .id(entity.getId())
                .idCargo(entity.getIdCargo())
                .idTurno(entity.getIdTurno())
                .nombres(entity.getNombres())
                .apellidos(entity.getApellidos())
                .dni(entity.getDni())
                .telefono(entity.getTelefono())
                .correo(entity.getCorreo())
                .fechaIngreso(entity.getFechaIngreso())
                .estadoLaboral(entity.getEstadoLaboral())
                .build();
    }
}
