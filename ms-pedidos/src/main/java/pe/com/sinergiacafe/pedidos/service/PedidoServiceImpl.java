package pe.com.sinergiacafe.pedidos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.pedidos.dto.EstadoPedidoResponseDTO;
import pe.com.sinergiacafe.pedidos.dto.PedidoRequestDTO;
import pe.com.sinergiacafe.pedidos.dto.PedidoResponseDTO;
import pe.com.sinergiacafe.pedidos.entity.EstadoPedidoEntity;
import pe.com.sinergiacafe.pedidos.entity.PedidoEntity;
import pe.com.sinergiacafe.pedidos.repository.EstadoPedidoRepository;
import pe.com.sinergiacafe.pedidos.repository.PedidoRepository;
import pe.com.sinergiacafe.pedidos.shared.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final EstadoPedidoRepository estadoPedidoRepository;

    @Override
    public List<PedidoResponseDTO> listarTodos() {
        return pedidoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public PedidoResponseDTO obtenerPorId(Long id) {
        PedidoEntity pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido", id));
        return toResponseDTO(pedido);
    }

    @Override
    public List<PedidoResponseDTO> listarPorCliente(Long idCliente) {
        return pedidoRepository.findByIdCliente(idCliente)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public PedidoResponseDTO crear(PedidoRequestDTO request) {
        EstadoPedidoEntity estado = estadoPedidoRepository.findById(request.getIdEstadoPedido())
                .orElseThrow(() -> new ResourceNotFoundException("EstadoPedido", request.getIdEstadoPedido()));

        PedidoEntity pedido = PedidoEntity.builder()
                .idCliente(request.getIdCliente())
                .idEmpleado(request.getIdEmpleado())
                .estadoPedido(estado)
                .observaciones(request.getObservaciones())
                .build();

        return toResponseDTO(pedidoRepository.save(pedido));
    }

    @Override
    public PedidoResponseDTO actualizarEstado(Long id, Long idEstadoPedido) {
        PedidoEntity pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido", id));

        EstadoPedidoEntity estado = estadoPedidoRepository.findById(idEstadoPedido)
                .orElseThrow(() -> new ResourceNotFoundException("EstadoPedido", idEstadoPedido));

        pedido.setEstadoPedido(estado);
        return toResponseDTO(pedidoRepository.save(pedido));
    }

    @Override
    public void eliminar(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pedido", id);
        }
        pedidoRepository.deleteById(id);
    }

    private PedidoResponseDTO toResponseDTO(PedidoEntity entity) {
        return PedidoResponseDTO.builder()
                .idPedido(entity.getIdPedido())
                .idCliente(entity.getIdCliente())
                .idEmpleado(entity.getIdEmpleado())
                .estadoPedido(EstadoPedidoResponseDTO.builder()
                        .idEstadoPedido(entity.getEstadoPedido().getIdEstadoPedido())
                        .nombre(entity.getEstadoPedido().getNombre())
                        .descripcion(entity.getEstadoPedido().getDescripcion())
                        .build())
                .fechaPedido(entity.getFechaPedido())
                .observaciones(entity.getObservaciones())
                .build();
    }
}