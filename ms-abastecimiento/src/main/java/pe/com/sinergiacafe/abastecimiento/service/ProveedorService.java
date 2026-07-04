package pe.com.sinergiacafe.abastecimiento.service;

import pe.com.sinergiacafe.abastecimiento.dto.ProveedorRequestDTO;
import pe.com.sinergiacafe.abastecimiento.dto.ProveedorResponseDTO;
import pe.com.sinergiacafe.abastecimiento.entity.ProveedorEntity;
import pe.com.sinergiacafe.abastecimiento.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.abastecimiento.repository.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProveedorService {

    private final ProveedorRepository repository;

    public List<ProveedorResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public ProveedorResponseDTO findById(Long id) {
        ProveedorEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con id: " + id));
        return toResponse(entity);
    }

    public ProveedorResponseDTO create(ProveedorRequestDTO dto) {
        ProveedorEntity entity = new ProveedorEntity();
        entity.setNombre(dto.getNombre());
        entity.setRuc(dto.getRuc());
        entity.setContacto(dto.getContacto());
        entity.setCondicionesPago(dto.getCondicionesPago());
        entity.setEstado(dto.getEstado());
        return toResponse(repository.save(entity));
    }

    public ProveedorResponseDTO update(Long id, ProveedorRequestDTO dto) {
        ProveedorEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con id: " + id));
        entity.setNombre(dto.getNombre());
        entity.setRuc(dto.getRuc());
        entity.setContacto(dto.getContacto());
        entity.setCondicionesPago(dto.getCondicionesPago());
        entity.setEstado(dto.getEstado());
        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Proveedor no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }

    private ProveedorResponseDTO toResponse(ProveedorEntity entity) {
        return ProveedorResponseDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .ruc(entity.getRuc())
                .contacto(entity.getContacto())
                .condicionesPago(entity.getCondicionesPago())
                .estado(entity.getEstado())
                .build();
    }
}
