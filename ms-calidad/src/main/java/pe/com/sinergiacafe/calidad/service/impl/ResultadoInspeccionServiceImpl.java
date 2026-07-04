package pe.com.sinergiacafe.calidad.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.calidad.dto.*;
import pe.com.sinergiacafe.calidad.entity.*;
import pe.com.sinergiacafe.calidad.repository.*;
import pe.com.sinergiacafe.calidad.service.ResultadoInspeccionService;
import pe.com.sinergiacafe.calidad.shared.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultadoInspeccionServiceImpl implements ResultadoInspeccionService {

    private final ResultadoInspeccionRepository repository;
    private final InspeccionRepository inspeccionRepository;
    private final EstandarCalidadRepository estandarRepository;

    @Override
    public List<ResultadoInspeccionResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public ResultadoInspeccionResponseDTO obtenerPorId(Long id) {
        ResultadoInspeccionEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ResultadoInspeccion", id));

        return toDTO(entity);
    }

    @Override
    public ResultadoInspeccionResponseDTO crear(ResultadoInspeccionRequestDTO request) {

        InspeccionEntity inspeccion = inspeccionRepository.findById(request.getIdInspeccion())
                .orElseThrow(() -> new ResourceNotFoundException("Inspeccion", request.getIdInspeccion()));

        EstandarCalidadEntity estandar = estandarRepository.findById(request.getIdEstandarCalidad())
                .orElseThrow(() -> new ResourceNotFoundException("EstandarCalidad", request.getIdEstandarCalidad()));

        ResultadoInspeccionEntity entity = ResultadoInspeccionEntity.builder()
                .inspeccion(inspeccion)
                .estandarCalidad(estandar)
                .cumple(request.getCumple())
                .observaciones(request.getObservaciones())
                .build();

        return toDTO(repository.save(entity));
    }

    @Override
    public ResultadoInspeccionResponseDTO actualizar(Long id, ResultadoInspeccionRequestDTO request) {

        ResultadoInspeccionEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ResultadoInspeccion", id));

        InspeccionEntity inspeccion = inspeccionRepository.findById(request.getIdInspeccion())
                .orElseThrow(() -> new ResourceNotFoundException("Inspeccion", request.getIdInspeccion()));

        EstandarCalidadEntity estandar = estandarRepository.findById(request.getIdEstandarCalidad())
                .orElseThrow(() -> new ResourceNotFoundException("EstandarCalidad", request.getIdEstandarCalidad()));

        entity.setInspeccion(inspeccion);
        entity.setEstandarCalidad(estandar);
        entity.setCumple(request.getCumple());
        entity.setObservaciones(request.getObservaciones());

        return toDTO(repository.save(entity));
    }

    @Override
    public void eliminar(Long id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("ResultadoInspeccion", id);
        }

        repository.deleteById(id);
    }

    private ResultadoInspeccionResponseDTO toDTO(ResultadoInspeccionEntity entity) {

        return ResultadoInspeccionResponseDTO.builder()
                .idResultadoInspeccion(entity.getIdResultadoInspeccion())
                .inspeccion(InspeccionSimpleDTO.builder()
                        .idInspeccion(entity.getInspeccion().getIdInspeccion())
                        .areaInspeccionada(entity.getInspeccion().getAreaInspeccionada())
                        .resultadoGeneral(entity.getInspeccion().getResultadoGeneral())
                        .build())
                .estandarCalidad(EstandarCalidadSimpleDTO.builder()
                        .idEstandarCalidad(entity.getEstandarCalidad().getIdEstandarCalidad())
                        .nombre(entity.getEstandarCalidad().getNombre())
                        .build())
                .cumple(entity.getCumple())
                .observaciones(entity.getObservaciones())
                .build();
    }
}