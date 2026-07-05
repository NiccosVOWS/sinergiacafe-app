package pe.com.sinergiacafe.finanzas.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.finanzas.dto.PresupuestoRequestDTO;
import pe.com.sinergiacafe.finanzas.dto.PresupuestoResponseDTO;
import pe.com.sinergiacafe.finanzas.entity.PresupuestoEntity;
import pe.com.sinergiacafe.finanzas.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.finanzas.repository.PresupuestoRepository;
import pe.com.sinergiacafe.finanzas.service.PresupuestoService;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PresupuestoServiceImpl implements PresupuestoService {

    private final PresupuestoRepository presupuestoRepository;

    @Override
    public List<PresupuestoResponseDTO> listarTodos() {
        return presupuestoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public List<PresupuestoResponseDTO> listarPorPeriodo(String periodo) {
        return presupuestoRepository.findByPeriodo(periodo)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public PresupuestoResponseDTO obtenerPorId(Long id) {
        PresupuestoEntity presupuesto = presupuestoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Presupuesto", id));
        return toResponseDTO(presupuesto);
    }

    @Override
    public PresupuestoResponseDTO crear(PresupuestoRequestDTO request) {
        PresupuestoEntity presupuesto = PresupuestoEntity.builder()
                .area(request.getArea())
                .periodo(request.getPeriodo())
                .montoAsignado(request.getMontoAsignado())
                .build();
        return toResponseDTO(presupuestoRepository.save(presupuesto));
    }

    @Override
    public PresupuestoResponseDTO actualizar(Long id, PresupuestoRequestDTO request) {
        PresupuestoEntity presupuesto = presupuestoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Presupuesto", id));
        presupuesto.setArea(request.getArea());
        presupuesto.setPeriodo(request.getPeriodo());
        presupuesto.setMontoAsignado(request.getMontoAsignado());
        return toResponseDTO(presupuestoRepository.save(presupuesto));
    }

    @Override
    public PresupuestoResponseDTO actualizarMontoEjecutado(Long id, BigDecimal montoEjecutado) {
        PresupuestoEntity presupuesto = presupuestoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Presupuesto", id));
        presupuesto.setMontoEjecutado(montoEjecutado);
        return toResponseDTO(presupuestoRepository.save(presupuesto));
    }

    @Override
    public void eliminar(Long id) {
        if (!presupuestoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Presupuesto", id);
        }
        presupuestoRepository.deleteById(id);
    }

    private PresupuestoResponseDTO toResponseDTO(PresupuestoEntity entity) {
        return PresupuestoResponseDTO.builder()
                .idPresupuesto(entity.getIdPresupuesto())
                .area(entity.getArea())
                .periodo(entity.getPeriodo())
                .montoAsignado(entity.getMontoAsignado())
                .montoEjecutado(entity.getMontoEjecutado())
                .build();
    }
}