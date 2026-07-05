package pe.com.sinergiacafe.recursoshumanos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.recursoshumanos.dto.AsistenciaRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.AsistenciaResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.CargoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.EmpleadoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.TurnoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.entity.AsistenciaEntity;
import pe.com.sinergiacafe.recursoshumanos.entity.EmpleadoEntity;
import pe.com.sinergiacafe.recursoshumanos.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.recursoshumanos.repository.AsistenciaRepository;
import pe.com.sinergiacafe.recursoshumanos.repository.EmpleadoRepository;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AsistenciaServiceImpl implements AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;
    private final EmpleadoRepository empleadoRepository;

    @Override
    public List<AsistenciaResponseDTO> listarTodos() {
        return asistenciaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public List<AsistenciaResponseDTO> listarPorEmpleado(Long idEmpleado) {
        return asistenciaRepository.findByEmpleado_IdEmpleado(idEmpleado)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public AsistenciaResponseDTO obtenerPorId(Long id) {
        AsistenciaEntity asistencia = asistenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asistencia", id));
        return toResponseDTO(asistencia);
    }

    @Override
    public AsistenciaResponseDTO registrar(AsistenciaRequestDTO request) {
        EmpleadoEntity empleado = empleadoRepository.findById(request.getIdEmpleado())
                .orElseThrow(() -> new ResourceNotFoundException("Empleado", request.getIdEmpleado()));

        AsistenciaEntity asistencia = AsistenciaEntity.builder()
                .empleado(empleado)
                .fecha(request.getFecha())
                .horaEntrada(request.getHoraEntrada())
                .horaSalida(request.getHoraSalida())
                .observaciones(request.getObservaciones())
                .build();

        return toResponseDTO(asistenciaRepository.save(asistencia));
    }

    @Override
    public AsistenciaResponseDTO actualizarHoraSalida(Long id, LocalTime horaSalida) {
        AsistenciaEntity asistencia = asistenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asistencia", id));
        asistencia.setHoraSalida(horaSalida);
        return toResponseDTO(asistenciaRepository.save(asistencia));
    }

    @Override
    public void eliminar(Long id) {
        if (!asistenciaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Asistencia", id);
        }
        asistenciaRepository.deleteById(id);
    }

    private AsistenciaResponseDTO toResponseDTO(AsistenciaEntity entity) {
        return AsistenciaResponseDTO.builder()
                .idAsistencia(entity.getIdAsistencia())
                .empleado(toEmpleadoDTO(entity.getEmpleado()))
                .fecha(entity.getFecha())
                .horaEntrada(entity.getHoraEntrada())
                .horaSalida(entity.getHoraSalida())
                .observaciones(entity.getObservaciones())
                .build();
    }

    private EmpleadoResponseDTO toEmpleadoDTO(EmpleadoEntity entity) {
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