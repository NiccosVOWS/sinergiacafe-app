package pe.com.sinergiacafe.calidad.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.calidad.dto.ResultadoInspeccionRequestDTO;
import pe.com.sinergiacafe.calidad.dto.ResultadoInspeccionResponseDTO;
import pe.com.sinergiacafe.calidad.entity.EstandarCalidadEntity;
import pe.com.sinergiacafe.calidad.entity.InspeccionEntity;
import pe.com.sinergiacafe.calidad.entity.ResultadoInspeccionEntity;
import pe.com.sinergiacafe.calidad.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.calidad.repository.EstandarCalidadRepository;
import pe.com.sinergiacafe.calidad.repository.InspeccionRepository;
import pe.com.sinergiacafe.calidad.repository.ResultadoInspeccionRepository;
import pe.com.sinergiacafe.calidad.service.ResultadoInspeccionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultadoInspeccionServiceImpl implements ResultadoInspeccionService {

    private final ResultadoInspeccionRepository resultadoInspeccionRepository;
    private final InspeccionRepository inspeccionRepository;
    private final EstandarCalidadRepository estandarCalidadRepository;
    private final InspeccionServiceImpl inspeccionService;
    private final EstandarCalidadServiceImpl estandarCalidadService;

    @Override
    public List<ResultadoInspeccionResponseDTO> listarTodos() {
        return resultadoInspeccionRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public List<ResultadoInspeccionResponseDTO> listarPorInspeccion(Long idInspeccion) {
        return resultadoInspeccionRepository.findByInspeccion_IdInspeccion(idInspeccion)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public ResultadoInspeccionResponseDTO obtenerPorId(Long id) {
        ResultadoInspeccionEntity entity = resultadoInspeccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ResultadoInspeccion", id));
        return toResponseDTO(entity);
    }

    @Override
    public ResultadoInspeccionResponseDTO registrar(ResultadoInspeccionRequestDTO request) {
        InspeccionEntity inspeccion = inspeccionRepository.findById(request.getIdInspeccion())
                .orElseThrow(() -> new ResourceNotFoundException("Inspeccion", request.getIdInspeccion()));
        EstandarCalidadEntity estandar = estandarCalidadRepository.findById(request.getIdEstandarCalidad())
                .orElseThrow(() -> new ResourceNotFoundException("EstandarCalidad", request.getIdEstandarCalidad()));

        ResultadoInspeccionEntity entity = ResultadoInspeccionEntity.builder()
                .inspeccion(inspeccion)
                .estandarCalidad(estandar)
                .cumple(request.getCumple())
                .observaciones(request.getObservaciones())
                .build();

        return toResponseDTO(resultadoInspeccionRepository.save(entity));
    }

    @Override
    public void eliminar(Long id) {
        if (!resultadoInspeccionRepository.existsById(id)) {
            throw new ResourceNotFoundException("ResultadoInspeccion", id);
        }
        resultadoInspeccionRepository.deleteById(id);
    }

    private ResultadoInspeccionResponseDTO toResponseDTO(ResultadoInspeccionEntity entity) {
        return ResultadoInspeccionResponseDTO.builder()
                .idResultadoInspeccion(entity.getIdResultadoInspeccion())
                .inspeccion(inspeccionService.toResponseDTO(entity.getInspeccion()))
                .estandarCalidad(estandarCalidadService.toResponseDTO(entity.getEstandarCalidad()))
                .cumple(entity.getCumple())
                .observaciones(entity.getObservaciones())
                .build();
    }
}