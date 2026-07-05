package pe.com.sinergiacafe.abastecimiento.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.abastecimiento.dto.OrdenCompraRequestDTO;
import pe.com.sinergiacafe.abastecimiento.dto.OrdenCompraResponseDTO;
import pe.com.sinergiacafe.abastecimiento.dto.ProveedorResponseDTO;
import pe.com.sinergiacafe.abastecimiento.entity.OrdenCompraEntity;
import pe.com.sinergiacafe.abastecimiento.entity.ProveedorEntity;
import pe.com.sinergiacafe.abastecimiento.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.abastecimiento.repository.OrdenCompraRepository;
import pe.com.sinergiacafe.abastecimiento.repository.ProveedorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdenCompraServiceImpl implements OrdenCompraService {

    private final OrdenCompraRepository ordenCompraRepository;
    private final ProveedorRepository proveedorRepository;

    @Override
    public List<OrdenCompraResponseDTO> listarTodos() {
        return ordenCompraRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public OrdenCompraResponseDTO obtenerPorId(Long id) {
        OrdenCompraEntity orden = ordenCompraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrdenCompra", id));
        return toResponseDTO(orden);
    }

    @Override
    public OrdenCompraResponseDTO crear(OrdenCompraRequestDTO request) {
        ProveedorEntity proveedor = proveedorRepository.findById(request.getIdProveedor())
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor", request.getIdProveedor()));

        OrdenCompraEntity orden = OrdenCompraEntity.builder()
                .proveedor(proveedor)
                .idEmpleado(request.getIdEmpleado())
                .build();

        return toResponseDTO(ordenCompraRepository.save(orden));
    }

    @Override
    public OrdenCompraResponseDTO actualizarEstado(Long id, String estado) {
        OrdenCompraEntity orden = ordenCompraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrdenCompra", id));
        orden.setEstado(OrdenCompraEntity.EstadoOrden.valueOf(estado));
        return toResponseDTO(ordenCompraRepository.save(orden));
    }

    @Override
    public void eliminar(Long id) {
        if (!ordenCompraRepository.existsById(id)) {
            throw new ResourceNotFoundException("OrdenCompra", id);
        }
        ordenCompraRepository.deleteById(id);
    }

    private OrdenCompraResponseDTO toResponseDTO(OrdenCompraEntity entity) {
        return OrdenCompraResponseDTO.builder()
                .idOrdenCompra(entity.getIdOrdenCompra())
                .proveedor(ProveedorResponseDTO.builder()
                        .idProveedor(entity.getProveedor().getIdProveedor())
                        .razonSocial(entity.getProveedor().getRazonSocial())
                        .ruc(entity.getProveedor().getRuc())
                        .contacto(entity.getProveedor().getContacto())
                        .telefono(entity.getProveedor().getTelefono())
                        .correo(entity.getProveedor().getCorreo())
                        .direccion(entity.getProveedor().getDireccion())
                        .activo(entity.getProveedor().getActivo())
                        .build())
                .idEmpleado(entity.getIdEmpleado())
                .fechaOrden(entity.getFechaOrden())
                .montoTotal(entity.getMontoTotal())
                .estado(entity.getEstado().name())
                .build();
    }
}