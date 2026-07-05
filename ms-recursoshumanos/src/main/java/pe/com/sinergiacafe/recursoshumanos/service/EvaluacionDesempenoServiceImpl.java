package pe.com.sinergiacafe.recursoshumanos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.recursoshumanos.dto.CargoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.EmpleadoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.EvaluacionDesempenoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.EvaluacionDesempenoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.TurnoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.entity.EmpleadoEntity;
import pe.com.sinergiacafe.recursoshumanos.entity.EvaluacionDesempenoEntity;
import pe.com.sinergiacafe.recursoshumanos.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.recursoshumanos.repository.EmpleadoRepository;
import pe.com.sinergiacafe.recursoshumanos.repository.EvaluacionDesempenoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EvaluacionDesempenoServiceImpl implements EvaluacionDesempenoService {

    private final EvaluacionDesempenoRepository evaluacionDesempenoRepository;
    private final EmpleadoRepository empleadoRepository;

    @Override
    public List<EvaluacionDesempenoResponseDTO> listarTodos() {
        return evaluacionDesempenoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public List<EvaluacionDesempenoResponseDTO> listarPorEmpleado(Long idEmpleado) {
        return evaluacionDesempenoRepository
                .findByEmpleado_IdEmpleadoOrderByFechaEvaluacionDesc(idEmpleado)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public EvaluacionDesempenoResponseDTO obtenerPorId(Long id) {
        EvaluacionDesempenoEntity evaluacion = evaluacionDesempenoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EvaluacionDesempeno", id));
        return toResponseDTO(evaluacion);
    }

    @Override
    public EvaluacionDesempenoResponseDTO registrar(EvaluacionDesempenoRequestDTO request) {
        EmpleadoEntity empleado = empleadoRepository.findById(request.getIdEmpleado())
                .orElseThrow(() -> new ResourceNotFoundException("Empleado", request.getIdEmpleado()));

        EvaluacionDesempenoEntity evaluacion = EvaluacionDesempenoEntity.builder()
                .empleado(empleado)
                .fechaEvaluacion(request.getFechaEvaluacion())
                .puntaje(request.getPuntaje())
                .criterios(request.getCriterios())
                .observaciones(request.getObservaciones())
                .build();

        return toResponseDTO(evaluacionDesempenoRepository.save(evaluacion));
    }

    @Override
    public void eliminar(Long id) {
        if (!evaluacionDesempenoRepository.existsById(id)) {
            throw new ResourceNotFoundException("EvaluacionDesempeno", id);
        }
        evaluacionDesempenoRepository.deleteById(id);
    }

    private EvaluacionDesempenoResponseDTO toResponseDTO(EvaluacionDesempenoEntity entity) {
        return EvaluacionDesempenoResponseDTO.builder()
                .idEvaluacionDesempeno(entity.getIdEvaluacionDesempeno())
                .empleado(toEmpleadoDTO(entity.getEmpleado()))
                .fechaEvaluacion(entity.getFechaEvaluacion())
                .puntaje(entity.getPuntaje())
                .criterios(entity.getCriterios())
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