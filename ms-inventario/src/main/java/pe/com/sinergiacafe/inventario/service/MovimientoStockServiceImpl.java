package pe.com.sinergiacafe.inventario.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.inventario.dto.MovimientoStockRequestDTO;
import pe.com.sinergiacafe.inventario.dto.MovimientoStockResponseDTO;
import pe.com.sinergiacafe.inventario.entity.MovimientoStockEntity;
import pe.com.sinergiacafe.inventario.entity.StockEntity;
import pe.com.sinergiacafe.inventario.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.inventario.repository.MovimientoStockRepository;
import pe.com.sinergiacafe.inventario.repository.StockRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimientoStockServiceImpl implements MovimientoStockService {

    private final MovimientoStockRepository movimientoStockRepository;
    private final StockRepository stockRepository;

    @Override
    public List<MovimientoStockResponseDTO> listarTodos() {
        return movimientoStockRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public MovimientoStockResponseDTO obtenerPorId(Long id) {
        MovimientoStockEntity entity = movimientoStockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MovimientoStock", id));
        return toResponseDTO(entity);
    }

    @Override
    public MovimientoStockResponseDTO crear(MovimientoStockRequestDTO request) {
        StockEntity stock = stockRepository.findById(request.getIdStock())
                .orElseThrow(() -> new ResourceNotFoundException("Stock", request.getIdStock()));

        MovimientoStockEntity entity = MovimientoStockEntity.builder()
                .stock(stock)
                .idEmpleado(request.getIdEmpleado())
                .tipoMovimiento(MovimientoStockEntity.TipoMovimiento.valueOf(request.getTipoMovimiento()))
                .cantidad(request.getCantidad())
                .motivo(request.getMotivo())
                .build();

        return toResponseDTO(movimientoStockRepository.save(entity));
    }

    @Override
    public void eliminar(Long id) {
        if (!movimientoStockRepository.existsById(id)) {
            throw new ResourceNotFoundException("MovimientoStock", id);
        }
        movimientoStockRepository.deleteById(id);
    }

    private MovimientoStockResponseDTO toResponseDTO(MovimientoStockEntity entity) {
        return MovimientoStockResponseDTO.builder()
                .idMovimientoStock(entity.getIdMovimientoStock())
                .idStock(entity.getStock().getIdStock())
                .idEmpleado(entity.getIdEmpleado())
                .tipoMovimiento(entity.getTipoMovimiento().name())
                .cantidad(entity.getCantidad())
                .motivo(entity.getMotivo())
                .fechaMovimiento(entity.getFechaMovimiento())
                .build();
    }
}