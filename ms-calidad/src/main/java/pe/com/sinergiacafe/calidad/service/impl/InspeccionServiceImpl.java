package pe.com.sinergiacafe.calidad.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.calidad.dto.InspeccionRequestDTO;
import pe.com.sinergiacafe.calidad.dto.InspeccionResponseDTO;
import pe.com.sinergiacafe.calidad.entity.InspeccionEntity;
import pe.com.sinergiacafe.calidad.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.calidad.repository.InspeccionRepository;
import pe.com.sinergiacafe.calidad.service.InspeccionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InspeccionServiceImpl implements InspeccionService {

    private final InspeccionRepository inspeccionRepository;

    @Override
    public List<InspeccionResponseDTO> listarTodos() {
        return inspeccionRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public List<InspeccionResponseDTO> listarPorArea(String area) {
        return inspeccionRepository.findByAreaInspeccionada(area)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public InspeccionResponseDTO obtenerPorId(Long id) {
        InspeccionEntity entity = inspeccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inspeccion", id));
        return toResponseDTO(entity);
    }

    @Override
    public InspeccionResponseDTO registrar(InspeccionRequestDTO request) {
        InspeccionEntity entity = InspeccionEntity.builder()
                .idEmpleado(request.getIdEmpleado())
                .areaInspeccionada(request.getAreaInspeccionada())
                .build();
        return toResponseDTO(inspeccionRepository.save(entity));
    }

    @Override
    public InspeccionResponseDTO actualizarResultado(Long id, String resultadoGeneral) {
        InspeccionEntity entity = inspeccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inspeccion", id));
        entity.setResultadoGeneral(
                InspeccionEntity.ResultadoGeneralInspeccion.valueOf(resultadoGeneral));
        return toResponseDTO(inspeccionRepository.save(entity));
    }

    @Override
    public void eliminar(Long id) {
        if (!inspeccionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Inspeccion", id);
        }
        inspeccionRepository.deleteById(id);
    }

    public InspeccionResponseDTO toResponseDTO(InspeccionEntity entity) {
        return InspeccionResponseDTO.builder()
                .idInspeccion(entity.getIdInspeccion())
                .idEmpleado(entity.getIdEmpleado())
                .fechaInspeccion(entity.getFechaInspeccion())
                .areaInspeccionada(entity.getAreaInspeccionada())
                .resultadoGeneral(entity.getResultadoGeneral().name())
                .build();
    }
}