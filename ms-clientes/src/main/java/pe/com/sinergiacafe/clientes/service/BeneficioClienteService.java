package pe.com.sinergiacafe.clientes.service;

import pe.com.sinergiacafe.clientes.dto.BeneficioClienteResponseDTO;

import java.util.List;

public interface BeneficioClienteService {

    List<BeneficioClienteResponseDTO> listarPorCliente(Long idCliente);
    BeneficioClienteResponseDTO obtenerPorId(Long id);
}