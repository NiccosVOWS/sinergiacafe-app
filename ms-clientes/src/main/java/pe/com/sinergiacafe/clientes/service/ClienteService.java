package pe.com.sinergiacafe.clientes.service;

import pe.com.sinergiacafe.clientes.dto.ClienteRequestDTO;
import pe.com.sinergiacafe.clientes.dto.ClienteResponseDTO;

import java.util.List;

public interface ClienteService {

    List<ClienteResponseDTO> listarTodos();
    ClienteResponseDTO obtenerPorId(Long id);
    ClienteResponseDTO obtenerPorDni(String dni);
    ClienteResponseDTO crear(ClienteRequestDTO request);
    ClienteResponseDTO actualizar(Long id, ClienteRequestDTO request);
    void eliminar(Long id);
}