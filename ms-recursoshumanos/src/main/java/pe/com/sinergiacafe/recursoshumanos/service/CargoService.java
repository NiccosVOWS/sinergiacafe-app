package pe.com.sinergiacafe.recursoshumanos.service;

import pe.com.sinergiacafe.recursoshumanos.dto.CargoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.CargoResponseDTO;

import java.util.List;

public interface CargoService {

    List<CargoResponseDTO> listarTodos();
    CargoResponseDTO obtenerPorId(Long id);
    CargoResponseDTO crear(CargoRequestDTO request);
    CargoResponseDTO actualizar(Long id, CargoRequestDTO request);
    void eliminar(Long id);
}