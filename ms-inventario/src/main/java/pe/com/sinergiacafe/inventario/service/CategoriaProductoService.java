package pe.com.sinergiacafe.inventario.service;

import pe.com.sinergiacafe.inventario.dto.CategoriaProductoRequestDTO;
import pe.com.sinergiacafe.inventario.dto.CategoriaProductoResponseDTO;
import pe.com.sinergiacafe.inventario.entity.CategoriaProductoEntity;
import pe.com.sinergiacafe.inventario.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.inventario.repository.CategoriaProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaProductoService {

    private final CategoriaProductoRepository repository;

    public List<CategoriaProductoResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public CategoriaProductoResponseDTO findById(Long id) {
        CategoriaProductoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CategoriaProducto no encontrado con id: " + id));
        return toResponse(entity);
    }

    public CategoriaProductoResponseDTO create(CategoriaProductoRequestDTO dto) {
        CategoriaProductoEntity entity = new CategoriaProductoEntity();
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        return toResponse(repository.save(entity));
    }

    public CategoriaProductoResponseDTO update(Long id, CategoriaProductoRequestDTO dto) {
        CategoriaProductoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CategoriaProducto no encontrado con id: " + id));
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("CategoriaProducto no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }

    private CategoriaProductoResponseDTO toResponse(CategoriaProductoEntity entity) {
        return CategoriaProductoResponseDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
