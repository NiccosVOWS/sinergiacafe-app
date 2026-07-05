package pe.com.sinergiacafe.administracion.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.administracion.dto.CategoriaGastoResponseDTO;
import pe.com.sinergiacafe.administracion.dto.GastoRequestDTO;
import pe.com.sinergiacafe.administracion.dto.GastoResponseDTO;
import pe.com.sinergiacafe.administracion.entity.CategoriaGastoEntity;
import pe.com.sinergiacafe.administracion.entity.GastoEntity;
import pe.com.sinergiacafe.administracion.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.administracion.repository.CategoriaGastoRepository;
import pe.com.sinergiacafe.administracion.repository.GastoRepository;
import pe.com.sinergiacafe.administracion.service.GastoService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GastoServiceImpl implements GastoService {

    private final GastoRepository gastoRepository;
    private final CategoriaGastoRepository categoriaGastoRepository;

    @Override
    public List<GastoResponseDTO> listarTodos() {
        return gastoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public GastoResponseDTO obtenerPorId(Long id) {

        GastoEntity gasto = gastoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gasto", id));

        return toResponseDTO(gasto);
    }

    @Override
    public GastoResponseDTO crear(GastoRequestDTO request) {

        CategoriaGastoEntity categoria = categoriaGastoRepository.findById(request.getIdCategoriaGasto())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría de gasto", request.getIdCategoriaGasto()));

        GastoEntity gasto = GastoEntity.builder()
                .categoriaGasto(categoria)
                .fechaGasto(request.getFechaGasto())
                .monto(request.getMonto())
                .descripcion(request.getDescripcion())
                .build();

        return toResponseDTO(gastoRepository.save(gasto));
    }

    @Override
    public GastoResponseDTO actualizar(Long id, GastoRequestDTO request) {

        GastoEntity gasto = gastoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gasto", id));

        CategoriaGastoEntity categoria = categoriaGastoRepository.findById(request.getIdCategoriaGasto())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría de gasto", request.getIdCategoriaGasto()));

        gasto.setCategoriaGasto(categoria);
        gasto.setFechaGasto(request.getFechaGasto());
        gasto.setMonto(request.getMonto());
        gasto.setDescripcion(request.getDescripcion());

        return toResponseDTO(gastoRepository.save(gasto));
    }

    @Override
    public void eliminar(Long id) {

        if (!gastoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Gasto", id);
        }

        gastoRepository.deleteById(id);
    }

    private GastoResponseDTO toResponseDTO(GastoEntity entity) {

        return GastoResponseDTO.builder()
                .idGasto(entity.getIdGasto())
                .categoriaGasto(
                        CategoriaGastoResponseDTO.builder()
                                .idCategoriaGasto(entity.getCategoriaGasto().getIdCategoriaGasto())
                                .nombre(entity.getCategoriaGasto().getNombre())
                                .descripcion(entity.getCategoriaGasto().getDescripcion())
                                .build()
                )
                .fechaGasto(entity.getFechaGasto())
                .monto(entity.getMonto())
                .descripcion(entity.getDescripcion())
                .build();
    }

}
