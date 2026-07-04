package pe.com.sinergiacafe.pedidos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.pedidos.dto.DetallePedidoRequestDTO;
import pe.com.sinergiacafe.pedidos.dto.DetallePedidoResponseDTO;
import pe.com.sinergiacafe.pedidos.entity.DetallePedidoEntity;
import pe.com.sinergiacafe.pedidos.entity.PedidoEntity;
import pe.com.sinergiacafe.pedidos.repository.DetallePedidoRepository;
import pe.com.sinergiacafe.pedidos.repository.PedidoRepository;
import pe.com.sinergiacafe.pedidos.shared.ResourceNotFoundException;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DetallePedidoServiceImpl implements DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;
    private final PedidoRepository pedidoRepository;

    @Override
    public List<DetallePedidoResponseDTO> listarPorPedido(Long idPedido) {
        return detallePedidoRepository.findByPedidoIdPedido(idPedido)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public DetallePedidoResponseDTO agregar(DetallePedidoRequestDTO request) {
        PedidoEntity pedido = pedidoRepository.findById(request.getIdPedido())
                .orElseThrow(() -> new ResourceNotFoundException("Pedido", request.getIdPedido()));

        DetallePedidoEntity detalle = DetallePedidoEntity.builder()
                .pedido(pedido)
                .idProducto(request.getIdProducto())
                .cantidad(request.getCantidad())
                .precioUnitario(request.getPrecioUnitario())
                .observaciones(request.getObservaciones())
                .build();

        return toResponseDTO(detallePedidoRepository.save(detalle));
    }

    @Override
    public void eliminar(Long id) {
        if (!detallePedidoRepository.existsById(id)) {
            throw new ResourceNotFoundException("DetallePedido", id);
        }
        detallePedidoRepository.deleteById(id);
    }

    private DetallePedidoResponseDTO toResponseDTO(DetallePedidoEntity entity) {
        BigDecimal subtotal = entity.getPrecioUnitario()
                .multiply(BigDecimal.valueOf(entity.getCantidad()));
        return DetallePedidoResponseDTO.builder()
                .idDetallePedido(entity.getIdDetallePedido())
                .idPedido(entity.getPedido().getIdPedido())
                .idProducto(entity.getIdProducto())
                .cantidad(entity.getCantidad())
                .precioUnitario(entity.getPrecioUnitario())
                .subtotal(subtotal)
                .observaciones(entity.getObservaciones())
                .build();
    }
}