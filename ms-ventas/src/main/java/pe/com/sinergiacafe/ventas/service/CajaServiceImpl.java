package pe.com.sinergiacafe.ventas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.ventas.dto.CajaRequestDTO;
import pe.com.sinergiacafe.ventas.dto.CajaResponseDTO;
import pe.com.sinergiacafe.ventas.entity.CajaEntity;
import pe.com.sinergiacafe.ventas.repository.CajaRepository;
import pe.com.sinergiacafe.ventas.shared.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CajaServiceImpl implements CajaService {

    private final CajaRepository cajaRepository;

    @Override
    public List<CajaResponseDTO> listarTodos() {
        return cajaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public CajaResponseDTO obtenerPorId(Long id) {
        CajaEntity caja = cajaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Caja", id));
        return toResponseDTO(caja);
    }

    @Override
    public CajaResponseDTO abrir(CajaRequestDTO request) {
        CajaEntity caja = CajaEntity.builder()
                .idEmpleado(request.getIdEmpleado())
                .montoInicial(request.getMontoInicial())
                .build();
        return toResponseDTO(cajaRepository.save(caja));
    }

    @Override
    public CajaResponseDTO cerrar(Long id) {
        CajaEntity caja = cajaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Caja", id));
        caja.setEstado(CajaEntity.EstadoCaja.cerrada);
        caja.setFechaCierre(LocalDateTime.now());
        return toResponseDTO(cajaRepository.save(caja));
    }

    private CajaResponseDTO toResponseDTO(CajaEntity entity) {
        return CajaResponseDTO.builder()
                .idCaja(entity.getIdCaja())
                .idEmpleado(entity.getIdEmpleado())
                .fechaApertura(entity.getFechaApertura())
                .montoInicial(entity.getMontoInicial())
                .fechaCierre(entity.getFechaCierre())
                .estado(entity.getEstado().name())
                .build();
    }
}