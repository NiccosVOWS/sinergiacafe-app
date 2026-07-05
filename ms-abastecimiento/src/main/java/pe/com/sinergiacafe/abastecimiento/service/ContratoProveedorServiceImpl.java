package pe.com.sinergiacafe.abastecimiento.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.abastecimiento.dto.ContratoProveedorRequestDTO;
import pe.com.sinergiacafe.abastecimiento.dto.ContratoProveedorResponseDTO;
import pe.com.sinergiacafe.abastecimiento.dto.ProveedorResponseDTO;
import pe.com.sinergiacafe.abastecimiento.entity.ContratoProveedorEntity;
import pe.com.sinergiacafe.abastecimiento.entity.ProveedorEntity;
import pe.com.sinergiacafe.abastecimiento.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.abastecimiento.repository.ContratoProveedorRepository;
import pe.com.sinergiacafe.abastecimiento.repository.ProveedorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratoProveedorServiceImpl implements ContratoProveedorService {

    private final ContratoProveedorRepository contratoProveedorRepository;
    private final ProveedorRepository proveedorRepository;

    @Override
    public List<ContratoProveedorResponseDTO> listarTodos() {
        return contratoProveedorRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public List<ContratoProveedorResponseDTO> listarPorProveedor(Long idProveedor) {
        return contratoProveedorRepository.findByProveedor_IdProveedor(idProveedor)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public ContratoProveedorResponseDTO obtenerPorId(Long id) {
        ContratoProveedorEntity contrato = contratoProveedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ContratoProveedor", id));
        return toResponseDTO(contrato);
    }

    @Override
    public ContratoProveedorResponseDTO crear(ContratoProveedorRequestDTO request) {
        ProveedorEntity proveedor = proveedorRepository.findById(request.getIdProveedor())
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor", request.getIdProveedor()));

        ContratoProveedorEntity contrato = ContratoProveedorEntity.builder()
                .proveedor(proveedor)
                .fechaInicio(request.getFechaInicio())
                .fechaFin(request.getFechaFin())
                .condiciones(request.getCondiciones())
                .build();

        return toResponseDTO(contratoProveedorRepository.save(contrato));
    }

    @Override
    public ContratoProveedorResponseDTO actualizar(Long id, ContratoProveedorRequestDTO request) {
        ContratoProveedorEntity contrato = contratoProveedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ContratoProveedor", id));
        ProveedorEntity proveedor = proveedorRepository.findById(request.getIdProveedor())
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor", request.getIdProveedor()));
        contrato.setProveedor(proveedor);
        contrato.setFechaInicio(request.getFechaInicio());
        contrato.setFechaFin(request.getFechaFin());
        contrato.setCondiciones(request.getCondiciones());
        return toResponseDTO(contratoProveedorRepository.save(contrato));
    }

    @Override
    public void eliminar(Long id) {
        if (!contratoProveedorRepository.existsById(id)) {
            throw new ResourceNotFoundException("ContratoProveedor", id);
        }
        contratoProveedorRepository.deleteById(id);
    }

    private ContratoProveedorResponseDTO toResponseDTO(ContratoProveedorEntity entity) {
        return ContratoProveedorResponseDTO.builder()
                .idContratoProveedor(entity.getIdContratoProveedor())
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
                .fechaInicio(entity.getFechaInicio())
                .fechaFin(entity.getFechaFin())
                .condiciones(entity.getCondiciones())
                .activo(entity.getActivo())
                .build();
    }
}