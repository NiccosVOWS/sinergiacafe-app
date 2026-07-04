package pe.com.sinergiacafe.recursoshumanos.service;

import pe.com.sinergiacafe.recursoshumanos.dto.CargoRequestDTO;
import pe.com.sinergiacafe.recursoshumanos.dto.CargoResponseDTO;
import pe.com.sinergiacafe.recursoshumanos.entity.CargoEntity;
import pe.com.sinergiacafe.recursoshumanos.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.recursoshumanos.repository.CargoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CargoService {

    private final CargoRepository repository;

    public List<CargoResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public CargoResponseDTO findById(Long id) {
        CargoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cargo no encontrado con id: " + id));
        return toResponse(entity);
    }

    public CargoResponseDTO create(CargoRequestDTO dto) {
        CargoEntity entity = new CargoEntity();
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        return toResponse(repository.save(entity));
    }

    public CargoResponseDTO update(Long id, CargoRequestDTO dto) {
        CargoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cargo no encontrado con id: " + id));
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Cargo no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }

    private CargoResponseDTO toResponse(CargoEntity entity) {
        return CargoResponseDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
