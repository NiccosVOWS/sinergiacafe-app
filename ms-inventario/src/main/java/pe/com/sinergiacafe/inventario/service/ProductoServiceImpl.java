package pe.com.sinergiacafe.inventario.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.inventario.dto.CategoriaProductoResponseDTO;
import pe.com.sinergiacafe.inventario.dto.ProductoRequestDTO;
import pe.com.sinergiacafe.inventario.dto.ProductoResponseDTO;
import pe.com.sinergiacafe.inventario.entity.CategoriaProductoEntity;
import pe.com.sinergiacafe.inventario.entity.ProductoEntity;
import pe.com.sinergiacafe.inventario.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.inventario.repository.CategoriaProductoRepository;
import pe.com.sinergiacafe.inventario.repository.ProductoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaProductoRepository categoriaProductoRepository;

    @Override
    public List<ProductoResponseDTO> listarTodos() {
        return productoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public ProductoResponseDTO obtenerPorId(Long id) {
        ProductoEntity producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", id));
        return toResponseDTO(producto);
    }

    @Override
    public ProductoResponseDTO crear(ProductoRequestDTO request) {
        CategoriaProductoEntity categoria = categoriaProductoRepository
                .findById(request.getIdCategoriaProducto())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "CategoriaProducto", request.getIdCategoriaProducto()));
        ProductoEntity producto = ProductoEntity.builder()
                .categoriaProducto(categoria)
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .precio(request.getPrecio())
                .disponible(request.getDisponible())
                .build();
        return toResponseDTO(productoRepository.save(producto));
    }

    @Override
    public ProductoResponseDTO actualizar(Long id, ProductoRequestDTO request) {
        ProductoEntity producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", id));
        CategoriaProductoEntity categoria = categoriaProductoRepository
                .findById(request.getIdCategoriaProducto())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "CategoriaProducto", request.getIdCategoriaProducto()));
        producto.setCategoriaProducto(categoria);
        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setDisponible(request.getDisponible());
        return toResponseDTO(productoRepository.save(producto));
    }

    @Override
    public void eliminar(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Producto", id);
        }
        productoRepository.deleteById(id);
    }

    private ProductoResponseDTO toResponseDTO(ProductoEntity entity) {
        return ProductoResponseDTO.builder()
                .idProducto(entity.getIdProducto())
                .categoriaProducto(CategoriaProductoResponseDTO.builder()
                        .idCategoriaProducto(entity.getCategoriaProducto().getIdCategoriaProducto())
                        .nombre(entity.getCategoriaProducto().getNombre())
                        .descripcion(entity.getCategoriaProducto().getDescripcion())
                        .build())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .precio(entity.getPrecio())
                .disponible(entity.getDisponible())
                .build();
    }
}