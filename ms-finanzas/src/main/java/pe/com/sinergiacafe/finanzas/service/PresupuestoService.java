package pe.com.sinergiacafe.finanzas.service;

import pe.com.sinergiacafe.finanzas.dto.PresupuestoRequestDTO;
import pe.com.sinergiacafe.finanzas.dto.PresupuestoResponseDTO;

import java.util.List;

public interface PresupuestoService {

    List<PresupuestoResponseDTO> listarTodos();

    PresupuestoResponseDTO obtenerPorId(Long id);

    PresupuestoResponseDTO crear(PresupuestoRequestDTO request);

    PresupuestoResponseDTO actualizar(Long id, PresupuestoRequestDTO request);

    void eliminar(Long id);

}