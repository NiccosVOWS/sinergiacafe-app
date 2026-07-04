package pe.com.sinergiacafe.ventas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.ventas.dto.CajaResponseDTO;
import pe.com.sinergiacafe.ventas.dto.CierreCajaRequestDTO;
import pe.com.sinergiacafe.ventas.dto.CierreCajaResponseDTO;
import pe.com.sinergiacafe.ventas.dto.MetodoPagoResponseDTO;
import pe.com.sinergiacafe.ventas.entity.CajaEntity;
import pe.com.sinergiacafe.ventas.entity.CierreCajaEntity;
import pe.com.sinergiacafe.ventas.entity.MetodoPagoEntity;
import pe.com.sinergiacafe.ventas.repository.CajaRepository;
import pe.com.sinergiacafe.ventas.repository.CierreCajaRepository;
import pe.com.sinergiacafe.ventas.repository.MetodoPagoRepository;
import pe.com.sinergiacafe.ventas.shared.ResourceNotFoundException;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CierreCajaServiceImpl implements CierreCajaService {

    private final CierreCajaRepository cierreCajaRepository;
    private final CajaRepository cajaRepository;
    private final MetodoPagoRepository metodoPagoRepository;

    @Override
    public List<CierreCajaResponseDTO> listarPorCaja(Long idCaja) {
        return cierreCajaRepository.findByCajaIdCaja(idCaja)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public CierreCajaResponseDTO registrar(CierreCajaRequestDTO request) {
        CajaEntity caja = cajaRepository.findById(request.getIdCaja())
                .orElseThrow(() -> new ResourceNotFoundException("Caja", request.getIdCaja()));
        MetodoPagoEntity metodoPago = metodoPagoRepository.findById(request.getIdMetodoPago())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "MetodoPago", request.getIdMetodoPago()));

        CierreCajaEntity cierreCaja = CierreCajaEntity.builder()
                .caja(caja)
                .metodoPago(metodoPago)
                .montoRegistrado(request.getMontoRegistrado())
                .diferencia(request.getDiferencia() != null
                        ? request.getDiferencia()
                        : BigDecimal.ZERO)
                .observaciones(request.getObservaciones())
                .build();

        return toResponseDTO(cierreCajaRepository.save(cierreCaja));
    }

    private CierreCajaResponseDTO toResponseDTO(CierreCajaEntity entity) {
        return CierreCajaResponseDTO.builder()
                .idCierreCaja(entity.getIdCierreCaja())
                .caja(CajaResponseDTO.builder()
                        .idCaja(entity.getCaja().getIdCaja())
                        .idEmpleado(entity.getCaja().getIdEmpleado())
                        .fechaApertura(entity.getCaja().getFechaApertura())
                        .montoInicial(entity.getCaja().getMontoInicial())
                        .fechaCierre(entity.getCaja().getFechaCierre())
                        .estado(entity.getCaja().getEstado().name())
                        .build())
                .metodoPago(MetodoPagoResponseDTO.builder()
                        .idMetodoPago(entity.getMetodoPago().getIdMetodoPago())
                        .nombre(entity.getMetodoPago().getNombre())
                        .descripcion(entity.getMetodoPago().getDescripcion())
                        .activo(entity.getMetodoPago().getActivo())
                        .build())
                .montoRegistrado(entity.getMontoRegistrado())
                .diferencia(entity.getDiferencia())
                .observaciones(entity.getObservaciones())
                .build();
    }
}