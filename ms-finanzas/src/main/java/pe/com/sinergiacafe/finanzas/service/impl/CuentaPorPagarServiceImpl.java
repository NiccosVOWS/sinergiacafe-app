package pe.com.sinergiacafe.finanzas.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.finanzas.dto.CuentaPorPagarRequestDTO;
import pe.com.sinergiacafe.finanzas.dto.CuentaPorPagarResponseDTO;
import pe.com.sinergiacafe.finanzas.entity.CuentaPorPagarEntity;
import pe.com.sinergiacafe.finanzas.repository.CuentaPorPagarRepository;
import pe.com.sinergiacafe.finanzas.service.CuentaPorPagarService;
import pe.com.sinergiacafe.finanzas.shared.ResourceNotFoundException;

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
                .estado(request.getEstado())
                .build();

        return toResponseDTO(cuentaPorPagarRepository.save(cuenta));
    }

    @Override
    public CuentaPorPagarResponseDTO actualizar(Long id, CuentaPorPagarRequestDTO request) {

        CuentaPorPagarEntity cuenta = cuentaPorPagarRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta por pagar", id));

        cuenta.setIdProveedor(request.getIdProveedor());
        cuenta.setIdOrdenCompra(request.getIdOrdenCompra());
        cuenta.setMonto(request.getMonto());
        cuenta.setFechaVencimiento(request.getFechaVencimiento());
        cuenta.setEstado(request.getEstado());

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
                .estado(entity.getEstado())
                .build();
    }

}