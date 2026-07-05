package pe.com.sinergiacafe.recursoshumanos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.recursoshumanos.dto.CargoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.EmpleadoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.RolResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.TurnoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.UsuarioRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.UsuarioResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.entity.EmpleadoEntity;
import pe.com.sinergiacafe.recursoshumanos.entity.RolEntity;
import pe.com.sinergiacafe.recursoshumanos.entity.UsuarioEntity;
import pe.com.sinergiacafe.recursoshumanos.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.recursoshumanos.repository.EmpleadoRepository;
import pe.com.sinergiacafe.recursoshumanos.repository.RolRepository;
import pe.com.sinergiacafe.recursoshumanos.repository.UsuarioRepository;

import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EmpleadoRepository empleadoRepository;
    private final RolRepository rolRepository;

    @Override
    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public UsuarioResponseDTO obtenerPorId(Long id) {
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", id));
        return toResponseDTO(usuario);
    }

    @Override
    public UsuarioResponseDTO obtenerPorUsername(String username) {
        UsuarioEntity usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuario con username " + username + " no encontrado"));
        return toResponseDTO(usuario);
    }

    @Override
    public UsuarioResponseDTO crear(UsuarioRequestDTO request) {
        EmpleadoEntity empleado = empleadoRepository.findById(request.getIdEmpleado())
                .orElseThrow(() -> new ResourceNotFoundException("Empleado", request.getIdEmpleado()));
        RolEntity rol = rolRepository.findById(request.getIdRol())
                .orElseThrow(() -> new ResourceNotFoundException("Rol", request.getIdRol()));

        UsuarioEntity usuario = UsuarioEntity.builder()
                .empleado(empleado)
                .rol(rol)
                .username(request.getUsername())
                .passwordHash(hashSHA2(request.getPassword()))
                .build();

        return toResponseDTO(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioResponseDTO actualizarRol(Long id, Long idRol) {
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", id));
        RolEntity rol = rolRepository.findById(idRol)
                .orElseThrow(() -> new ResourceNotFoundException("Rol", idRol));
        usuario.setRol(rol);
        return toResponseDTO(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioResponseDTO actualizarEstado(Long id, Boolean activo) {
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", id));
        usuario.setActivo(activo);
        return toResponseDTO(usuarioRepository.save(usuario));
    }

    @Override
    public void eliminar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario", id);
        }
        usuarioRepository.deleteById(id);
    }

    private String hashSHA2(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            return HexFormat.of().formatHex(hash);
        } catch (Exception e) {
            throw new RuntimeException("Error al hashear la contraseña", e);
        }
    }

    public UsuarioResponseDTO toResponseDTO(UsuarioEntity entity) {
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