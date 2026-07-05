package pe.com.sinergiacafe.recursoshumanos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.recursoshumanos.dto.CargoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.EmpleadoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.EmpleadoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.TurnoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.entity.CargoEntity;
import pe.com.sinergiacafe.recursoshumanos.entity.EmpleadoEntity;
import pe.com.sinergiacafe.recursoshumanos.entity.TurnoEntity;
import pe.com.sinergiacafe.recursoshumanos.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.recursoshumanos.repository.CargoRepository;
import pe.com.sinergiacafe.recursoshumanos.repository.EmpleadoRepository;
import pe.com.sinergiacafe.recursoshumanos.repository.TurnoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final CargoRepository cargoRepository;
    private final TurnoRepository turnoRepository;

    @Override
    public List<EmpleadoResponseDTO> listarTodos() {
        return empleadoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public EmpleadoResponseDTO obtenerPorId(Long id) {
        EmpleadoEntity empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado", id));
        return toResponseDTO(empleado);
    }

    @Override
    public EmpleadoResponseDTO obtenerPorDni(String dni) {
        EmpleadoEntity empleado = empleadoRepository.findByDni(dni)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado con DNI " + dni + " no encontrado"));
        return toResponseDTO(empleado);
    }

    @Override
    public EmpleadoResponseDTO crear(EmpleadoRequestDTO request) {
        CargoEntity cargo = cargoRepository.findById(request.getIdCargo())
                .orElseThrow(() -> new ResourceNotFoundException("Cargo", request.getIdCargo()));
        TurnoEntity turno = turnoRepository.findById(request.getIdTurno())
                .orElseThrow(() -> new ResourceNotFoundException("Turno", request.getIdTurno()));

        EmpleadoEntity empleado = EmpleadoEntity.builder()
                .cargo(cargo)
                .turno(turno)
                .nombres(request.getNombres())
                .apellidos(request.getApellidos())
                .dni(request.getDni())
                .telefono(request.getTelefono())
                .correo(request.getCorreo())
                .fechaIngreso(request.getFechaIngreso())
                .build();

        return toResponseDTO(empleadoRepository.save(empleado));
    }

    @Override
    public EmpleadoResponseDTO actualizar(Long id, EmpleadoRequestDTO request) {
        EmpleadoEntity empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado", id));
        CargoEntity cargo = cargoRepository.findById(request.getIdCargo())
                .orElseThrow(() -> new ResourceNotFoundException("Cargo", request.getIdCargo()));
        TurnoEntity turno = turnoRepository.findById(request.getIdTurno())
                .orElseThrow(() -> new ResourceNotFoundException("Turno", request.getIdTurno()));

        empleado.setCargo(cargo);
        empleado.setTurno(turno);
        empleado.setNombres(request.getNombres());
        empleado.setApellidos(request.getApellidos());
        empleado.setDni(request.getDni());
        empleado.setTelefono(request.getTelefono());
        empleado.setCorreo(request.getCorreo());
        empleado.setFechaIngreso(request.getFechaIngreso());

        return toResponseDTO(empleadoRepository.save(empleado));
    }

    @Override
    public EmpleadoResponseDTO actualizarEstadoLaboral(Long id, String estadoLaboral) {
        EmpleadoEntity empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado", id));
        empleado.setEstadoLaboral(EmpleadoEntity.EstadoLaboral.valueOf(estadoLaboral));
        return toResponseDTO(empleadoRepository.save(empleado));
    }

    @Override
    public void eliminar(Long id) {
        if (!empleadoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Empleado", id);
        }
        empleadoRepository.deleteById(id);
    }

    public EmpleadoResponseDTO toResponseDTO(EmpleadoEntity entity) {
        return EmpleadoResponseDTO.builder()
                .idEmpleado(entity.getIdEmpleado())
                .cargo(CargoResponseDTO.builder()
                        .idCargo(entity.getCargo().getIdCargo())
                        .nombre(entity.getCargo().getNombre())
                        .descripcion(entity.getCargo().getDescripcion())
                        .build())
                .turno(TurnoResponseDTO.builder()
                        .idTurno(entity.getTurno().getIdTurno())
                        .nombre(entity.getTurno().getNombre())
                        .horaInicio(entity.getTurno().getHoraInicio())
                        .horaFin(entity.getTurno().getHoraFin())
                        .build())
                .nombres(entity.getNombres())
                .apellidos(entity.getApellidos())
                .dni(entity.getDni())
                .telefono(entity.getTelefono())
                .correo(entity.getCorreo())
                .fechaIngreso(entity.getFechaIngreso())
                .estadoLaboral(entity.getEstadoLaboral().name())
                .build();
    }
}