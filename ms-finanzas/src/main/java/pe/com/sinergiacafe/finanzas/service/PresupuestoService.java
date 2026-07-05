package pe.com.sinergiacafe.finanzas.service;

import pe.com.sinergiacafe.finanzas.dto.PresupuestoRequestDTO;
import pe.com.sinergiacafe.finanzas.dto.PresupuestoResponseDTO;

import java.math.BigDecimal;
import java.util.List;

public interface PresupuestoService {

    List<PresupuestoResponseDTO> listarTodos();
    List<PresupuestoResponseDTO> listarPorPeriodo(String periodo);
    PresupuestoResponseDTO obtenerPorId(Long id);
    PresupuestoResponseDTO crear(PresupuestoRequestDTO request);
    PresupuestoResponseDTO actualizar(Long id, PresupuestoRequestDTO request);
    PresupuestoResponseDTO actualizarMontoEjecutado(Long id, BigDecimal montoEjecutado);
    void eliminar(Long id);
}