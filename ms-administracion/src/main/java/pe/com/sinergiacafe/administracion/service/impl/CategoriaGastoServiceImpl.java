package pe.com.sinergiacafe.administracion.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.administracion.dto.CategoriaGastoRequestDTO;
import pe.com.sinergiacafe.administracion.dto.CategoriaGastoResponseDTO;
import pe.com.sinergiacafe.administracion.entity.CategoriaGastoEntity;
import pe.com.sinergiacafe.administracion.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.administracion.repository.CategoriaGastoRepository;
import pe.com.sinergiacafe.administracion.service.CategoriaGastoService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaGastoServiceImpl implements CategoriaGastoService {

    private final CategoriaGastoRepository categoriaGastoRepository;

    @Override
    public List<CategoriaGastoResponseDTO> listarTodos() {
        return categoriaGastoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public CategoriaGastoResponseDTO obtenerPorId(Long id) {
        CategoriaGastoEntity categoria = categoriaGastoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría de gasto", id));

        return toResponseDTO(categoria);
    }

    @Override
    public CategoriaGastoResponseDTO crear(CategoriaGastoRequestDTO request) {

        CategoriaGastoEntity categoria = CategoriaGastoEntity.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .build();

        return toResponseDTO(categoriaGastoRepository.save(categoria));
    }

    @Override
    public CategoriaGastoResponseDTO actualizar(Long id, CategoriaGastoRequestDTO request) {

        CategoriaGastoEntity categoria = categoriaGastoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría de gasto", id));

        categoria.setNombre(request.getNombre());
        categoria.setDescripcion(request.getDescripcion());

        return toResponseDTO(categoriaGastoRepository.save(categoria));
    }

    @Override
    public void eliminar(Long id) {

        if (!categoriaGastoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoría de gasto", id);
        }

        categoriaGastoRepository.deleteById(id);
    }

    private CategoriaGastoResponseDTO toResponseDTO(CategoriaGastoEntity entity) {

        return CategoriaGastoResponseDTO.builder()
                .idCategoriaGasto(entity.getIdCategoriaGasto())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build();
    }
}
