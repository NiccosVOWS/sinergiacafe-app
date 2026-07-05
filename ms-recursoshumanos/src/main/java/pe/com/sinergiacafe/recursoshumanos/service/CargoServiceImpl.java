package pe.com.sinergiacafe.recursoshumanos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.recursoshumanos.dto.CargoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.CargoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.entity.CargoEntity;
import pe.com.sinergiacafe.recursoshumanos.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.recursoshumanos.repository.CargoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CargoServiceImpl implements CargoService {

    private final CargoRepository cargoRepository;

    @Override
    public List<CargoResponseDTO> listarTodos() {
        return cargoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public CargoResponseDTO obtenerPorId(Long id) {
        CargoEntity cargo = cargoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cargo", id));
        return toResponseDTO(cargo);
    }

    @Override
    public CargoResponseDTO crear(CargoRequestDTO request) {
        CargoEntity cargo = CargoEntity.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .build();
        return toResponseDTO(cargoRepository.save(cargo));
    }

    @Override
    public CargoResponseDTO actualizar(Long id, CargoRequestDTO request) {
        CargoEntity cargo = cargoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cargo", id));
        cargo.setNombre(request.getNombre());
        cargo.setDescripcion(request.getDescripcion());
        return toResponseDTO(cargoRepository.save(cargo));
    }

    @Override
    public void eliminar(Long id) {
        if (!cargoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cargo", id);
        }
        cargoRepository.deleteById(id);
    }

    private CargoResponseDTO toResponseDTO(CargoEntity entity) {
        return CargoResponseDTO.builder()
                .idCargo(entity.getIdCargo())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build();
    }
}