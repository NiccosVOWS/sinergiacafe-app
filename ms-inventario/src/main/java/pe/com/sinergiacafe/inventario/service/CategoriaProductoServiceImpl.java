package pe.com.sinergiacafe.inventario.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.inventario.dto.CategoriaProductoRequestDTO;
import pe.com.sinergiacafe.inventario.dto.CategoriaProductoResponseDTO;
import pe.com.sinergiacafe.inventario.entity.CategoriaProductoEntity;
import pe.com.sinergiacafe.inventario.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.inventario.repository.CategoriaProductoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaProductoServiceImpl implements CategoriaProductoService {

    private final CategoriaProductoRepository categoriaProductoRepository;

    @Override
    public List<CategoriaProductoResponseDTO> listarTodos() {
        return categoriaProductoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public CategoriaProductoResponseDTO obtenerPorId(Long id) {
        CategoriaProductoEntity entity = categoriaProductoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CategoriaProducto", id));
        return toResponseDTO(entity);
    }

    @Override
    public CategoriaProductoResponseDTO crear(CategoriaProductoRequestDTO request) {
        CategoriaProductoEntity entity = CategoriaProductoEntity.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .build();
        return toResponseDTO(categoriaProductoRepository.save(entity));
    }

    @Override
    public CategoriaProductoResponseDTO actualizar(Long id, CategoriaProductoRequestDTO request) {
        CategoriaProductoEntity entity = categoriaProductoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CategoriaProducto", id));
        entity.setNombre(request.getNombre());
        entity.setDescripcion(request.getDescripcion());
        return toResponseDTO(categoriaProductoRepository.save(entity));
    }

    @Override
    public void eliminar(Long id) {
        if (!categoriaProductoRepository.existsById(id)) {
            throw new ResourceNotFoundException("CategoriaProducto", id);
        }
        categoriaProductoRepository.deleteById(id);
    }

    private CategoriaProductoResponseDTO toResponseDTO(CategoriaProductoEntity entity) {
        return CategoriaProductoResponseDTO.builder()
                .idCategoriaProducto(entity.getIdCategoriaProducto())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build();
    }
}