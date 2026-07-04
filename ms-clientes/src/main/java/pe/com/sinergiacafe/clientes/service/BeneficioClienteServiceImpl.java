package pe.com.sinergiacafe.clientes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.clientes.dto.BeneficioClienteResponseDTO;
import pe.com.sinergiacafe.clientes.dto.ProgramaFidelizacionResponseDTO;
import pe.com.sinergiacafe.clientes.entity.BeneficioClienteEntity;
import pe.com.sinergiacafe.clientes.repository.BeneficioClienteRepository;
import pe.com.sinergiacafe.clientes.shared.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BeneficioClienteServiceImpl implements BeneficioClienteService {

    private final BeneficioClienteRepository beneficioClienteRepository;

    @Override
    public List<BeneficioClienteResponseDTO> listarPorCliente(Long idCliente) {
        return beneficioClienteRepository.findByClienteIdCliente(idCliente)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public BeneficioClienteResponseDTO obtenerPorId(Long id) {
        BeneficioClienteEntity beneficio = beneficioClienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BeneficioCliente", id));
        return toResponseDTO(beneficio);
    }

    private BeneficioClienteResponseDTO toResponseDTO(BeneficioClienteEntity entity) {
        return BeneficioClienteResponseDTO.builder()
                .idBeneficioCliente(entity.getIdBeneficioCliente())
                .idCliente(entity.getCliente().getIdCliente())
                .nombreCliente(entity.getCliente().getNombres() + " "
                        + entity.getCliente().getApellidos())
                .programaFidelizacion(ProgramaFidelizacionResponseDTO.builder()
                        .idProgramaFidelizacion(entity.getProgramaFidelizacion()
                                .getIdProgramaFidelizacion())
                        .nombre(entity.getProgramaFidelizacion().getNombre())
                        .descripcion(entity.getProgramaFidelizacion().getDescripcion())
                        .puntosPorSol(entity.getProgramaFidelizacion().getPuntosPorSol())
                        .minPuntosCanje(entity.getProgramaFidelizacion().getMinPuntosCanje())
                        .activo(entity.getProgramaFidelizacion().getActivo())
                        .fechaInicio(entity.getProgramaFidelizacion().getFechaInicio())
                        .fechaFin(entity.getProgramaFidelizacion().getFechaFin())
                        .build())
                .puntosAcumulados(entity.getPuntosAcumulados())
                .puntosCanjeados(entity.getPuntosCanjeados())
                .puntosDisponibles(entity.getPuntosDisponibles())
                .ultimaActualizacion(entity.getUltimaActualizacion())
                .build();
    }
}