package pe.com.sinergiacafe.finanzas.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.finanzas.dto.CuentaPorPagarRequestDTO;
import pe.com.sinergiacafe.finanzas.dto.CuentaPorPagarResponseDTO;
import pe.com.sinergiacafe.finanzas.entity.CuentaPorPagarEntity;
import pe.com.sinergiacafe.finanzas.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.finanzas.repository.CuentaPorPagarRepository;
import pe.com.sinergiacafe.finanzas.service.CuentaPorPagarService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CuentaPorPagarServiceImpl implements CuentaPorPagarService {

    private final CuentaPorPagarRepository cuentaPorPagarRepository;

    @Override
    public List<CuentaPorPagarResponseDTO> listarTodos() {
        return cuentaPorPagarRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public List<CuentaPorPagarResponseDTO> listarPorProveedor(Long idProveedor) {
        return cuentaPorPagarRepository.findByIdProveedor(idProveedor)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public CuentaPorPagarResponseDTO obtenerPorId(Long id) {
        CuentaPorPagarEntity cuenta = cuentaPorPagarRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta por pagar", id));
        return toResponseDTO(cuenta);
    }

    @Override
    public CuentaPorPagarResponseDTO crear(CuentaPorPagarRequestDTO request) {
        CuentaPorPagarEntity cuenta = CuentaPorPagarEntity.builder()
                .idProveedor(request.getIdProveedor())
                .idOrdenCompra(request.getIdOrdenCompra())
                .monto(request.getMonto())
                .fechaVencimiento(request.getFechaVencimiento())
                .build();
        return toResponseDTO(cuentaPorPagarRepository.save(cuenta));
    }

    @Override
    public CuentaPorPagarResponseDTO actualizarEstado(Long id, String estado) {
        CuentaPorPagarEntity cuenta = cuentaPorPagarRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta por pagar", id));
        cuenta.setEstado(CuentaPorPagarEntity.EstadoCuentaPorPagar.valueOf(estado));
        return toResponseDTO(cuentaPorPagarRepository.save(cuenta));
    }

    @Override
    public void eliminar(Long id) {
        if (!cuentaPorPagarRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cuenta por pagar", id);
        }
        cuentaPorPagarRepository.deleteById(id);
    }

    private CuentaPorPagarResponseDTO toResponseDTO(CuentaPorPagarEntity entity) {
        return CuentaPorPagarResponseDTO.builder()
                .idCuentaPorPagar(entity.getIdCuentaPorPagar())
                .idProveedor(entity.getIdProveedor())
                .idOrdenCompra(entity.getIdOrdenCompra())
                .monto(entity.getMonto())
                .fechaVencimiento(entity.getFechaVencimiento())
                .estado(entity.getEstado().name())
                .build();
    }
}