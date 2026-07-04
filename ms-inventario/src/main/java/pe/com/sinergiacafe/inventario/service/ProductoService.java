package pe.com.sinergiacafe.inventario.service;

import pe.com.sinergiacafe.inventario.dto.ProductoRequestDTO;
import pe.com.sinergiacafe.inventario.dto.ProductoResponseDTO;
import pe.com.sinergiacafe.inventario.entity.ProductoEntity;
import pe.com.sinergiacafe.inventario.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.inventario.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository repository;

    public List<ProductoResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public ProductoResponseDTO findById(Long id) {
        ProductoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));
        return toResponse(entity);
    }

    public ProductoResponseDTO create(ProductoRequestDTO dto) {
        ProductoEntity entity = new ProductoEntity();
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setPrecio(dto.getPrecio());
        entity.setDisponible(dto.getDisponible());
        entity.setCategoriaProductoId(dto.getCategoriaProductoId());
        return toResponse(repository.save(entity));
    }

    public ProductoResponseDTO update(Long id, ProductoRequestDTO dto) {
        ProductoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setPrecio(dto.getPrecio());
        entity.setDisponible(dto.getDisponible());
        entity.setCategoriaProductoId(dto.getCategoriaProductoId());
        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Producto no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }

    private ProductoResponseDTO toResponse(ProductoEntity entity) {
        return ProductoResponseDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .precio(entity.getPrecio())
                .disponible(entity.getDisponible())
                .categoriaProductoId(entity.getCategoriaProductoId())
                .build();
    }
}
