package pe.com.sinergiacafe.marketing.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.marketing.dto.CampanaMarketingRequestDTO;
import pe.com.sinergiacafe.marketing.dto.CampanaMarketingResponseDTO;
import pe.com.sinergiacafe.marketing.entity.CampanaMarketingEntity;
import pe.com.sinergiacafe.marketing.repository.CampanaMarketingRepository;
import pe.com.sinergiacafe.marketing.service.CampanaMarketingService;
import pe.com.sinergiacafe.marketing.shared.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampanaMarketingServiceImpl implements CampanaMarketingService {

    private final CampanaMarketingRepository repository;

    @Override
    public List<CampanaMarketingResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public CampanaMarketingResponseDTO obtenerPorId(Long id) {
        CampanaMarketingEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaña de marketing", id));

        return toDTO(entity);
    }

    @Override
    public CampanaMarketingResponseDTO crear(CampanaMarketingRequestDTO request) {

        CampanaMarketingEntity entity = CampanaMarketingEntity.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .canal(request.getCanal())
                .fechaInicio(request.getFechaInicio())
                .fechaFin(request.getFechaFin())
                .presupuesto(request.getPresupuesto())
                .build();

        return toDTO(repository.save(entity));
    }

    @Override
    public CampanaMarketingResponseDTO actualizar(Long id, CampanaMarketingRequestDTO request) {

        CampanaMarketingEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaña de marketing", id));

        entity.setNombre(request.getNombre());
        entity.setDescripcion(request.getDescripcion());
        entity.setCanal(request.getCanal());
        entity.setFechaInicio(request.getFechaInicio());
        entity.setFechaFin(request.getFechaFin());
        entity.setPresupuesto(request.getPresupuesto());

        return toDTO(repository.save(entity));
    }

    @Override
    public void eliminar(Long id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Campaña de marketing", id);
        }

        repository.deleteById(id);
    }

    private CampanaMarketingResponseDTO toDTO(CampanaMarketingEntity entity) {
        return CampanaMarketingResponseDTO.builder()
                .idCampanaMarketing(entity.getIdCampanaMarketing())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .canal(entity.getCanal())
                .fechaInicio(entity.getFechaInicio())
                .fechaFin(entity.getFechaFin())
                .presupuesto(entity.getPresupuesto())
                .build();
    }
}