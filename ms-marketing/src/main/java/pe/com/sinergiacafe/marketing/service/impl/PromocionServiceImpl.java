package pe.com.sinergiacafe.marketing.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.marketing.dto.*;
import pe.com.sinergiacafe.marketing.entity.*;
import pe.com.sinergiacafe.marketing.repository.*;
import pe.com.sinergiacafe.marketing.service.PromocionService;
import pe.com.sinergiacafe.marketing.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromocionServiceImpl implements PromocionService {

    private final PromocionRepository repository;
    private final CampanaMarketingRepository campanaRepository;

    @Override
    public List<PromocionResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public PromocionResponseDTO obtenerPorId(Long id) {
        PromocionEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Promoción", id));

        return toDTO(entity);
    }

    @Override
    public PromocionResponseDTO crear(PromocionRequestDTO request) {

        CampanaMarketingEntity campana = campanaRepository.findById(request.getIdCampanaMarketing())
                .orElseThrow(() -> new ResourceNotFoundException("Campaña de marketing", request.getIdCampanaMarketing()));

        PromocionEntity entity = PromocionEntity.builder()
                .campanaMarketing(campana)
                .descripcion(request.getDescripcion())
                .tipoDescuento(request.getTipoDescuento())
                .valorDescuento(request.getValorDescuento())
                .fechaInicio(request.getFechaInicio())
                .fechaFin(request.getFechaFin())
                .build();

        return toDTO(repository.save(entity));
    }

    @Override
    public PromocionResponseDTO actualizar(Long id, PromocionRequestDTO request) {

        PromocionEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Promoción", id));

        CampanaMarketingEntity campana = campanaRepository.findById(request.getIdCampanaMarketing())
                .orElseThrow(() -> new ResourceNotFoundException("Campaña de marketing", request.getIdCampanaMarketing()));

        entity.setCampanaMarketing(campana);
        entity.setDescripcion(request.getDescripcion());
        entity.setTipoDescuento(request.getTipoDescuento());
        entity.setValorDescuento(request.getValorDescuento());
        entity.setFechaInicio(request.getFechaInicio());
        entity.setFechaFin(request.getFechaFin());

        return toDTO(repository.save(entity));
    }

    @Override
    public void eliminar(Long id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Promoción", id);
        }

        repository.deleteById(id);
    }

    private PromocionResponseDTO toDTO(PromocionEntity entity) {

        return PromocionResponseDTO.builder()
                .idPromocion(entity.getIdPromocion())
                .campanaMarketing(CampanaMarketingResponseDTO.builder()
                        .idCampanaMarketing(entity.getCampanaMarketing().getIdCampanaMarketing())
                        .nombre(entity.getCampanaMarketing().getNombre())
                        .descripcion(entity.getCampanaMarketing().getDescripcion())
                        .canal(entity.getCampanaMarketing().getCanal())
                        .fechaInicio(entity.getCampanaMarketing().getFechaInicio())
                        .fechaFin(entity.getCampanaMarketing().getFechaFin())
                        .presupuesto(entity.getCampanaMarketing().getPresupuesto())
                        .build())
                .descripcion(entity.getDescripcion())
                .tipoDescuento(entity.getTipoDescuento().name())
                .valorDescuento(entity.getValorDescuento())
                .fechaInicio(entity.getFechaInicio())
                .fechaFin(entity.getFechaFin())
                .activo(entity.getActivo())
                .build();
    }
}