package pe.com.sinergiacafe.abastecimiento.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.abastecimiento.dto.OrdenCompraResponseDTO;
import pe.com.sinergiacafe.abastecimiento.dto.ProveedorResponseDTO;
import pe.com.sinergiacafe.abastecimiento.dto.RecepcionMercaderiaRequestDTO;
import pe.com.sinergiacafe.abastecimiento.dto.RecepcionMercaderiaResponseDTO;
import pe.com.sinergiacafe.abastecimiento.entity.OrdenCompraEntity;
import pe.com.sinergiacafe.abastecimiento.entity.RecepcionMercaderiaEntity;
import pe.com.sinergiacafe.abastecimiento.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.abastecimiento.repository.OrdenCompraRepository;
import pe.com.sinergiacafe.abastecimiento.repository.RecepcionMercaderiaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecepcionMercaderiaServiceImpl implements RecepcionMercaderiaService {

    private final RecepcionMercaderiaRepository recepcionMercaderiaRepository;
    private final OrdenCompraRepository ordenCompraRepository;

    @Override
    public List<RecepcionMercaderiaResponseDTO> listarTodos() {
        return recepcionMercaderiaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public List<RecepcionMercaderiaResponseDTO> listarPorOrden(Long idOrdenCompra) {
        return recepcionMercaderiaRepository.findByOrdenCompra_IdOrdenCompra(idOrdenCompra)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public RecepcionMercaderiaResponseDTO obtenerPorId(Long id) {
        RecepcionMercaderiaEntity recepcion = recepcionMercaderiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RecepcionMercaderia", id));
        return toResponseDTO(recepcion);
    }

    @Override
    public RecepcionMercaderiaResponseDTO registrar(RecepcionMercaderiaRequestDTO request) {
        OrdenCompraEntity orden = ordenCompraRepository.findById(request.getIdOrdenCompra())
                .orElseThrow(() -> new ResourceNotFoundException("OrdenCompra", request.getIdOrdenCompra()));

        RecepcionMercaderiaEntity recepcion = RecepcionMercaderiaEntity.builder()
                .ordenCompra(orden)
                .idEmpleado(request.getIdEmpleado())
                .conforme(request.getConforme())
                .observaciones(request.getObservaciones())
                .build();

        return toResponseDTO(recepcionMercaderiaRepository.save(recepcion));
    }

    @Override
    public void eliminar(Long id) {
        if (!recepcionMercaderiaRepository.existsById(id)) {
            throw new ResourceNotFoundException("RecepcionMercaderia", id);
        }
        recepcionMercaderiaRepository.deleteById(id);
    }

    private RecepcionMercaderiaResponseDTO toResponseDTO(RecepcionMercaderiaEntity entity) {
        return RecepcionMercaderiaResponseDTO.builder()
                .idRecepcionMercaderia(entity.getIdRecepcionMercaderia())
                .ordenCompra(OrdenCompraResponseDTO.builder()
                        .idOrdenCompra(entity.getOrdenCompra().getIdOrdenCompra())
                        .proveedor(ProveedorResponseDTO.builder()
                                .idProveedor(entity.getOrdenCompra().getProveedor().getIdProveedor())
                                .razonSocial(entity.getOrdenCompra().getProveedor().getRazonSocial())
                                .ruc(entity.getOrdenCompra().getProveedor().getRuc())
                                .contacto(entity.getOrdenCompra().getProveedor().getContacto())
                                .telefono(entity.getOrdenCompra().getProveedor().getTelefono())
                                .correo(entity.getOrdenCompra().getProveedor().getCorreo())
                                .direccion(entity.getOrdenCompra().getProveedor().getDireccion())
                                .activo(entity.getOrdenCompra().getProveedor().getActivo())
                                .build())
                        .idEmpleado(entity.getOrdenCompra().getIdEmpleado())
                        .fechaOrden(entity.getOrdenCompra().getFechaOrden())
                        .montoTotal(entity.getOrdenCompra().getMontoTotal())
                        .estado(entity.getOrdenCompra().getEstado().name())
                        .build())
                .idEmpleado(entity.getIdEmpleado())
                .fechaRecepcion(entity.getFechaRecepcion())
                .conforme(entity.getConforme())
                .observaciones(entity.getObservaciones())
                .build();
    }
}