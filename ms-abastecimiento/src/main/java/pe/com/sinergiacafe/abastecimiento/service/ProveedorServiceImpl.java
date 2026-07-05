package pe.com.sinergiacafe.abastecimiento.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.abastecimiento.dto.ProveedorRequestDTO;
import pe.com.sinergiacafe.abastecimiento.dto.ProveedorResponseDTO;
import pe.com.sinergiacafe.abastecimiento.entity.ProveedorEntity;
import pe.com.sinergiacafe.abastecimiento.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.abastecimiento.repository.ProveedorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;

    @Override
    public List<ProveedorResponseDTO> listarTodos() {
        return proveedorRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public ProveedorResponseDTO obtenerPorId(Long id) {
        ProveedorEntity proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor", id));
        return toResponseDTO(proveedor);
    }

    @Override
    public ProveedorResponseDTO crear(ProveedorRequestDTO request) {
        ProveedorEntity proveedor = ProveedorEntity.builder()
                .razonSocial(request.getRazonSocial())
                .ruc(request.getRuc())
                .contacto(request.getContacto())
                .telefono(request.getTelefono())
                .correo(request.getCorreo())
                .direccion(request.getDireccion())
                .build();
        return toResponseDTO(proveedorRepository.save(proveedor));
    }

    @Override
    public ProveedorResponseDTO actualizar(Long id, ProveedorRequestDTO request) {
        ProveedorEntity proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor", id));
        proveedor.setRazonSocial(request.getRazonSocial());
        proveedor.setRuc(request.getRuc());
        proveedor.setContacto(request.getContacto());
        proveedor.setTelefono(request.getTelefono());
        proveedor.setCorreo(request.getCorreo());
        proveedor.setDireccion(request.getDireccion());
        return toResponseDTO(proveedorRepository.save(proveedor));
    }

    @Override
    public void eliminar(Long id) {
        if (!proveedorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Proveedor", id);
        }
        proveedorRepository.deleteById(id);
    }

    private ProveedorResponseDTO toResponseDTO(ProveedorEntity entity) {
        return ProveedorResponseDTO.builder()
                .idProveedor(entity.getIdProveedor())
                .razonSocial(entity.getRazonSocial())
                .ruc(entity.getRuc())
                .contacto(entity.getContacto())
                .telefono(entity.getTelefono())
                .correo(entity.getCorreo())
                .direccion(entity.getDireccion())
                .activo(entity.getActivo())
                .build();
    }
}