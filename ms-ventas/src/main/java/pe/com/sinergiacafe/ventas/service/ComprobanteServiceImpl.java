package pe.com.sinergiacafe.ventas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.ventas.dto.ComprobanteRequestDTO;
import pe.com.sinergiacafe.ventas.dto.ComprobanteResponseDTO;
import pe.com.sinergiacafe.ventas.dto.MetodoPagoResponseDTO;
import pe.com.sinergiacafe.ventas.dto.TipoComprobanteResponseDTO;
import pe.com.sinergiacafe.ventas.entity.ComprobanteEntity;
import pe.com.sinergiacafe.ventas.entity.MetodoPagoEntity;
import pe.com.sinergiacafe.ventas.entity.TipoComprobanteEntity;
import pe.com.sinergiacafe.ventas.repository.ComprobanteRepository;
import pe.com.sinergiacafe.ventas.repository.MetodoPagoRepository;
import pe.com.sinergiacafe.ventas.repository.TipoComprobanteRepository;
import pe.com.sinergiacafe.ventas.shared.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComprobanteServiceImpl implements ComprobanteService {

    private final ComprobanteRepository comprobanteRepository;
    private final TipoComprobanteRepository tipoComprobanteRepository;
    private final MetodoPagoRepository metodoPagoRepository;

    @Override
    public List<ComprobanteResponseDTO> listarTodos() {
        return comprobanteRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public ComprobanteResponseDTO obtenerPorId(Long id) {
        ComprobanteEntity comprobante = comprobanteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comprobante", id));
        return toResponseDTO(comprobante);
    }

    @Override
    public List<ComprobanteResponseDTO> listarPorPedido(Long idPedido) {
        return comprobanteRepository.findByIdPedido(idPedido)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public ComprobanteResponseDTO emitir(ComprobanteRequestDTO request) {
        TipoComprobanteEntity tipoComprobante = tipoComprobanteRepository
                .findById(request.getIdTipoComprobante())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "TipoComprobante", request.getIdTipoComprobante()));
        MetodoPagoEntity metodoPago = metodoPagoRepository
                .findById(request.getIdMetodoPago())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "MetodoPago", request.getIdMetodoPago()));

        ComprobanteEntity comprobante = ComprobanteEntity.builder()
                .idPedido(request.getIdPedido())
                .tipoComprobante(tipoComprobante)
                .metodoPago(metodoPago)
                .serie(request.getSerie())
                .numero(request.getNumero())
                .subtotal(request.getSubtotal())
                .igv(request.getIgv())
                .total(request.getTotal())
                .build();

        return toResponseDTO(comprobanteRepository.save(comprobante));
    }

    private ComprobanteResponseDTO toResponseDTO(ComprobanteEntity entity) {
        return ComprobanteResponseDTO.builder()
                .idComprobante(entity.getIdComprobante())
                .idPedido(entity.getIdPedido())
                .tipoComprobante(TipoComprobanteResponseDTO.builder()
                        .idTipoComprobante(entity.getTipoComprobante().getIdTipoComprobante())
                        .nombre(entity.getTipoComprobante().getNombre())
                        .descripcion(entity.getTipoComprobante().getDescripcion())
                        .build())
                .metodoPago(MetodoPagoResponseDTO.builder()
                        .idMetodoPago(entity.getMetodoPago().getIdMetodoPago())
                        .nombre(entity.getMetodoPago().getNombre())
                        .descripcion(entity.getMetodoPago().getDescripcion())
                        .activo(entity.getMetodoPago().getActivo())
                        .build())
                .serie(entity.getSerie())
                .numero(entity.getNumero())
                .subtotal(entity.getSubtotal())
                .igv(entity.getIgv())
                .total(entity.getTotal())
                .fechaEmision(entity.getFechaEmision())
                .build();
    }
}