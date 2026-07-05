package pe.com.sinergiacafe.calidad.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.calidad.dto.EstandarCalidadRequestDTO;
import pe.com.sinergiacafe.calidad.dto.EstandarCalidadResponseDTO;
import pe.com.sinergiacafe.calidad.entity.EstandarCalidadEntity;
import pe.com.sinergiacafe.calidad.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.calidad.repository.EstandarCalidadRepository;
import pe.com.sinergiacafe.calidad.service.EstandarCalidadService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstandarCalidadServiceImpl implements EstandarCalidadService {

    private final EstandarCalidadRepository estandarCalidadRepository;

    @Override
    public List<EstandarCalidadResponseDTO> listarTodos() {
        return estandarCalidadRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public List<EstandarCalidadResponseDTO> listarPorAplicableA(String aplicableA) {
        return estandarCalidadRepository.findByAplicableA(aplicableA)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public EstandarCalidadResponseDTO obtenerPorId(Long id) {
        EstandarCalidadEntity entity = estandarCalidadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EstandarCalidad", id));
        return toResponseDTO(entity);
    }

    @Override
    public EstandarCalidadResponseDTO crear(EstandarCalidadRequestDTO request) {
        EstandarCalidadEntity entity = EstandarCalidadEntity.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .aplicableA(request.getAplicableA())
                .build();
        return toResponseDTO(estandarCalidadRepository.save(entity));
    }

    @Override
    public EstandarCalidadResponseDTO actualizar(Long id, EstandarCalidadRequestDTO request) {
        EstandarCalidadEntity entity = estandarCalidadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EstandarCalidad", id));
        entity.setNombre(request.getNombre());
        entity.setDescripcion(request.getDescripcion());
        entity.setAplicableA(request.getAplicableA());
        return toResponseDTO(estandarCalidadRepository.save(entity));
    }

    @Override
    public void eliminar(Long id) {
        if (!estandarCalidadRepository.existsById(id)) {
            throw new ResourceNotFoundException("EstandarCalidad", id);
        }
        estandarCalidadRepository.deleteById(id);
    }

    public EstandarCalidadResponseDTO toResponseDTO(EstandarCalidadEntity entity) {
        return EstandarCalidadResponseDTO.builder()
                .idEstandarCalidad(entity.getIdEstandarCalidad())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .aplicableA(entity.getAplicableA())
                .build();
    }
}