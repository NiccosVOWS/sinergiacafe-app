package pe.com.sinergiacafe.abastecimiento.service;

import pe.com.sinergiacafe.abastecimiento.dto.OrdenCompraRequestDTO;
import pe.com.sinergiacafe.abastecimiento.dto.OrdenCompraResponseDTO;

import java.util.List;

public interface OrdenCompraService {

    List<OrdenCompraResponseDTO> listarTodos();
    OrdenCompraResponseDTO obtenerPorId(Long id);
    OrdenCompraResponseDTO crear(OrdenCompraRequestDTO request);
    OrdenCompraResponseDTO actualizarEstado(Long id, String estado);
    void eliminar(Long id);
}