package pe.com.sinergiacafe.abastecimiento.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.abastecimiento.dto.DetalleOrdenCompraRequestDTO;
import pe.com.sinergiacafe.abastecimiento.dto.DetalleOrdenCompraResponseDTO;
import pe.com.sinergiacafe.abastecimiento.entity.DetalleOrdenCompraEntity;
import pe.com.sinergiacafe.abastecimiento.entity.OrdenCompraEntity;
import pe.com.sinergiacafe.abastecimiento.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.abastecimiento.repository.DetalleOrdenCompraRepository;
import pe.com.sinergiacafe.abastecimiento.repository.OrdenCompraRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetalleOrdenCompraServiceImpl implements DetalleOrdenCompraService {

    private final DetalleOrdenCompraRepository detalleOrdenCompraRepository;
    private final OrdenCompraRepository ordenCompraRepository;

    @Override
    public List<DetalleOrdenCompraResponseDTO> listarTodos() {
        return detalleOrdenCompraRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public List<DetalleOrdenCompraResponseDTO> listarPorOrden(Long idOrdenCompra) {
        return detalleOrdenCompraRepository.findByOrdenCompra_IdOrdenCompra(idOrdenCompra)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public DetalleOrdenCompraResponseDTO obtenerPorId(Long id) {
        DetalleOrdenCompraEntity detalle = detalleOrdenCompraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DetalleOrdenCompra", id));
        return toResponseDTO(detalle);
    }

    @Override
    public DetalleOrdenCompraResponseDTO agregar(DetalleOrdenCompraRequestDTO request) {
        OrdenCompraEntity orden = ordenCompraRepository.findById(request.getIdOrdenCompra())
                .orElseThrow(() -> new ResourceNotFoundException("OrdenCompra", request.getIdOrdenCompra()));

        DetalleOrdenCompraEntity detalle = DetalleOrdenCompraEntity.builder()
                .ordenCompra(orden)
                .idInsumo(request.getIdInsumo())
                .cantidad(request.getCantidad())
                .precioUnitario(request.getPrecioUnitario())
                .build();

        return toResponseDTO(detalleOrdenCompraRepository.save(detalle));
    }

    @Override
    public void eliminar(Long id) {
        if (!detalleOrdenCompraRepository.existsById(id)) {
            throw new ResourceNotFoundException("DetalleOrdenCompra", id);
        }
        detalleOrdenCompraRepository.deleteById(id);
    }

    private DetalleOrdenCompraResponseDTO toResponseDTO(DetalleOrdenCompraEntity entity) {
        return DetalleOrdenCompraResponseDTO.builder()
                .idDetalleOrdenCompra(entity.getIdDetalleOrdenCompra())
                .idOrdenCompra(entity.getOrdenCompra().getIdOrdenCompra())
                .idInsumo(entity.getIdInsumo())
                .cantidad(entity.getCantidad())
                .precioUnitario(entity.getPrecioUnitario())
                .subtotal(entity.getSubtotal())
                .build();
    }
}