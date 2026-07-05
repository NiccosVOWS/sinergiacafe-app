package pe.com.sinergiacafe.recursoshumanos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.recursoshumanos.dto.AuditoriaRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.AuditoriaResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.CargoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.EmpleadoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.RolResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.TurnoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.UsuarioResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.entity.AuditoriaEntity;
import pe.com.sinergiacafe.recursoshumanos.entity.EmpleadoEntity;
import pe.com.sinergiacafe.recursoshumanos.entity.UsuarioEntity;
import pe.com.sinergiacafe.recursoshumanos.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.recursoshumanos.repository.AuditoriaRepository;
import pe.com.sinergiacafe.recursoshumanos.repository.UsuarioRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditoriaServiceImpl implements AuditoriaService {

    private final AuditoriaRepository auditoriaRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public List<AuditoriaResponseDTO> listarTodos() {
        return auditoriaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public List<AuditoriaResponseDTO> listarPorUsuario(Long idUsuario) {
        return auditoriaRepository.findByUsuario_IdUsuario(idUsuario)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public AuditoriaResponseDTO obtenerPorId(Long id) {
        AuditoriaEntity auditoria = auditoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Auditoria", id));
        return toResponseDTO(auditoria);
    }

    @Override
    public AuditoriaResponseDTO registrar(AuditoriaRequestDTO request) {
        UsuarioEntity usuario = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", request.getIdUsuario()));

        AuditoriaEntity auditoria = AuditoriaEntity.builder()
                .usuario(usuario)
                .modulo(request.getModulo())
                .accion(request.getAccion())
                .tablaAfectada(request.getTablaAfectada())
                .idRegistro(request.getIdRegistro())
                .detalle(request.getDetalle())
                .build();

        return toResponseDTO(auditoriaRepository.save(auditoria));
    }

    private AuditoriaResponseDTO toResponseDTO(AuditoriaEntity entity) {
        return AuditoriaResponseDTO.builder()
                .idAuditoria(entity.getIdAuditoria())
                .usuario(toUsuarioDTO(entity.getUsuario()))
                .modulo(entity.getModulo())
                .accion(entity.getAccion())
                .tablaAfectada(entity.getTablaAfectada())
                .idRegistro(entity.getIdRegistro())
                .detalle(entity.getDetalle())
                .fechaHora(entity.getFechaHora())
                .build();
    }

    private UsuarioResponseDTO toUsuarioDTO(UsuarioEntity entity) {
        return UsuarioResponseDTO.builder()
                .idUsuario(entity.getIdUsuario())
                .empleado(toEmpleadoDTO(entity.getEmpleado()))
                .rol(RolResponseDTO.builder()
                        .idRol(entity.getRol().getIdRol())
                        .nombre(entity.getRol().getNombre())
                        .descripcion(entity.getRol().getDescripcion())
                        .activo(entity.getRol().getActivo())
                        .build())
                .username(entity.getUsername())
                .activo(entity.getActivo())
                .fechaCreacion(entity.getFechaCreacion())
                .ultimoAcceso(entity.getUltimoAcceso())
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