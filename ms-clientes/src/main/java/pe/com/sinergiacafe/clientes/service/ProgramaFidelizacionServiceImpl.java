package pe.com.sinergiacafe.clientes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.clientes.dto.ProgramaFidelizacionResponseDTO;
import pe.com.sinergiacafe.clientes.entity.ProgramaFidelizacionEntity;
import pe.com.sinergiacafe.clientes.repository.ProgramaFidelizacionRepository;
import pe.com.sinergiacafe.clientes.shared.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramaFidelizacionServiceImpl implements ProgramaFidelizacionService {

    private final ProgramaFidelizacionRepository programaFidelizacionRepository;

    @Override
    public List<ProgramaFidelizacionResponseDTO> listarTodos() {
        return programaFidelizacionRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public List<ProgramaFidelizacionResponseDTO> listarActivos() {
        return programaFidelizacionRepository.findByActivoTrue()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public ProgramaFidelizacionResponseDTO obtenerPorId(Long id) {
        ProgramaFidelizacionEntity programa = programaFidelizacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProgramaFidelizacion", id));
        return toResponseDTO(programa);
    }

    private ProgramaFidelizacionResponseDTO toResponseDTO(ProgramaFidelizacionEntity entity) {
        return ProgramaFidelizacionResponseDTO.builder()
                .idProgramaFidelizacion(entity.getIdProgramaFidelizacion())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .puntosPorSol(entity.getPuntosPorSol())
                .minPuntosCanje(entity.getMinPuntosCanje())
                .activo(entity.getActivo())
                .fechaInicio(entity.getFechaInicio())
                .fechaFin(entity.getFechaFin())
                .build();
    }
}