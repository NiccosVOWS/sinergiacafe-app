package pe.com.sinergiacafe.recursoshumanos.service;

import pe.com.sinergiacafe.recursoshumanos.dto.AuditoriaRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.AuditoriaResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.entity.AuditoriaEntity;
import pe.com.sinergiacafe.recursoshumanos.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.recursoshumanos.repository.AuditoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditoriaService {

    private final AuditoriaRepository repository;

    public List<AuditoriaResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public AuditoriaResponseDTO findById(Long id) {
        AuditoriaEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Auditoria no encontrado con id: " + id));
        return toResponse(entity);
    }

    public AuditoriaResponseDTO create(AuditoriaRequestDTO dto) {
        AuditoriaEntity entity = new AuditoriaEntity();
        entity.setUsuarioId(dto.getUsuarioId());
        entity.setAccion(dto.getAccion());
        entity.setFecha(dto.getFecha());
        entity.setDetalle(dto.getDetalle());
        return toResponse(repository.save(entity));
    }

    public AuditoriaResponseDTO update(Long id, AuditoriaRequestDTO dto) {
        AuditoriaEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Auditoria no encontrado con id: " + id));
        entity.setUsuarioId(dto.getUsuarioId());
        entity.setAccion(dto.getAccion());
        entity.setFecha(dto.getFecha());
        entity.setDetalle(dto.getDetalle());
        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Auditoria no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }

    private AuditoriaResponseDTO toResponse(AuditoriaEntity entity) {
        return AuditoriaResponseDTO.builder()
                .id(entity.getId())
                .usuarioId(entity.getUsuarioId())
                .accion(entity.getAccion())
                .fecha(entity.getFecha())
                .detalle(entity.getDetalle())
                .build();
    }
}
