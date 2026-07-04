package pe.com.sinergiacafe.recursoshumanos.service;

import pe.com.sinergiacafe.recursoshumanos.dto.EvaluacionDesempenoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.EvaluacionDesempenoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.entity.EvaluacionDesempenoEntity;
import pe.com.sinergiacafe.recursoshumanos.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.recursoshumanos.repository.EvaluacionDesempenoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EvaluacionDesempenoService {

    private final EvaluacionDesempenoRepository repository;

    public List<EvaluacionDesempenoResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public EvaluacionDesempenoResponseDTO findById(Long id) {
        EvaluacionDesempenoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EvaluacionDesempeno no encontrado con id: " + id));
        return toResponse(entity);
    }

    public EvaluacionDesempenoResponseDTO create(EvaluacionDesempenoRequestDTO dto) {
        EvaluacionDesempenoEntity entity = new EvaluacionDesempenoEntity();
        entity.setEmpleadoId(dto.getEmpleadoId());
        entity.setFecha(dto.getFecha());
        entity.setCriterios(dto.getCriterios());
        entity.setPuntaje(dto.getPuntaje());
        entity.setObservaciones(dto.getObservaciones());
        return toResponse(repository.save(entity));
    }

    public EvaluacionDesempenoResponseDTO update(Long id, EvaluacionDesempenoRequestDTO dto) {
        EvaluacionDesempenoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EvaluacionDesempeno no encontrado con id: " + id));
        entity.setEmpleadoId(dto.getEmpleadoId());
        entity.setFecha(dto.getFecha());
        entity.setCriterios(dto.getCriterios());
        entity.setPuntaje(dto.getPuntaje());
        entity.setObservaciones(dto.getObservaciones());
        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("EvaluacionDesempeno no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }

    private EvaluacionDesempenoResponseDTO toResponse(EvaluacionDesempenoEntity entity) {
        return EvaluacionDesempenoResponseDTO.builder()
                .id(entity.getId())
                .empleadoId(entity.getEmpleadoId())
                .fecha(entity.getFecha())
                .criterios(entity.getCriterios())
                .puntaje(entity.getPuntaje())
                .observaciones(entity.getObservaciones())
                .build();
    }
}
