package pe.com.sinergiacafe.finanzas.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.finanzas.dto.ReporteFinancieroRequestDTO;
import pe.com.sinergiacafe.finanzas.dto.ReporteFinancieroResponseDTO;
import pe.com.sinergiacafe.finanzas.entity.ReporteFinancieroEntity;
import pe.com.sinergiacafe.finanzas.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.finanzas.repository.ReporteFinancieroRepository;
import pe.com.sinergiacafe.finanzas.service.ReporteFinancieroService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReporteFinancieroServiceImpl implements ReporteFinancieroService {

    private final ReporteFinancieroRepository reporteFinancieroRepository;

    @Override
    public List<ReporteFinancieroResponseDTO> listarTodos() {
        return reporteFinancieroRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public ReporteFinancieroResponseDTO obtenerPorId(Long id) {
        ReporteFinancieroEntity reporte = reporteFinancieroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reporte financiero", id));
        return toResponseDTO(reporte);
    }

    @Override
    public ReporteFinancieroResponseDTO obtenerPorPeriodo(String periodo) {
        ReporteFinancieroEntity reporte = reporteFinancieroRepository.findByPeriodo(periodo)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Reporte financiero para el período " + periodo + " no encontrado"));
        return toResponseDTO(reporte);
    }

    @Override
    public ReporteFinancieroResponseDTO generar(ReporteFinancieroRequestDTO request) {
        ReporteFinancieroEntity reporte = ReporteFinancieroEntity.builder()
                .periodo(request.getPeriodo())
                .ingresos(request.getIngresos())
                .egresos(request.getEgresos())
                .utilidadNeta(request.getIngresos().subtract(request.getEgresos()))
                .build();
        return toResponseDTO(reporteFinancieroRepository.save(reporte));
    }

    @Override
    public void eliminar(Long id) {
        if (!reporteFinancieroRepository.existsById(id)) {
            throw new ResourceNotFoundException("Reporte financiero", id);
        }
        reporteFinancieroRepository.deleteById(id);
    }

    private ReporteFinancieroResponseDTO toResponseDTO(ReporteFinancieroEntity entity) {
        return ReporteFinancieroResponseDTO.builder()
                .idReporteFinanciero(entity.getIdReporteFinanciero())
                .periodo(entity.getPeriodo())
                .ingresos(entity.getIngresos())
                .egresos(entity.getEgresos())
                .utilidadNeta(entity.getUtilidadNeta())
                .fechaGeneracion(entity.getFechaGeneracion())
                .build();
    }
}