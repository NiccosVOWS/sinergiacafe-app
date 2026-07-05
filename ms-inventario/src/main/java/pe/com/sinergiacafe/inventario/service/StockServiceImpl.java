package pe.com.sinergiacafe.inventario.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.inventario.dto.StockRequestDTO;
import pe.com.sinergiacafe.inventario.dto.StockResponseDTO;
import pe.com.sinergiacafe.inventario.entity.StockEntity;
import pe.com.sinergiacafe.inventario.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.inventario.repository.StockRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Override
    public List<StockResponseDTO> listarTodos() {
        return stockRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public StockResponseDTO obtenerPorId(Long id) {
        StockEntity stock = stockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock", id));
        return toResponseDTO(stock);
    }

    @Override
    public StockResponseDTO crear(StockRequestDTO request) {
        StockEntity stock = StockEntity.builder()
                .idInsumo(request.getIdInsumo())
                .cantidadActual(request.getCantidadActual())
                .cantidadMinima(request.getCantidadMinima())
                .build();
        return toResponseDTO(stockRepository.save(stock));
    }

    @Override
    public StockResponseDTO actualizar(Long id, StockRequestDTO request) {
        StockEntity stock = stockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock", id));
        stock.setIdInsumo(request.getIdInsumo());
        stock.setCantidadActual(request.getCantidadActual());
        stock.setCantidadMinima(request.getCantidadMinima());
        return toResponseDTO(stockRepository.save(stock));
    }

    @Override
    public void eliminar(Long id) {
        if (!stockRepository.existsById(id)) {
            throw new ResourceNotFoundException("Stock", id);
        }
        stockRepository.deleteById(id);
    }

    private StockResponseDTO toResponseDTO(StockEntity entity) {
        return StockResponseDTO.builder()
                .idStock(entity.getIdStock())
                .idInsumo(entity.getIdInsumo())
                .cantidadActual(entity.getCantidadActual())
                .cantidadMinima(entity.getCantidadMinima())
                .ultimaActualizacion(entity.getUltimaActualizacion())
                .build();
    }
}