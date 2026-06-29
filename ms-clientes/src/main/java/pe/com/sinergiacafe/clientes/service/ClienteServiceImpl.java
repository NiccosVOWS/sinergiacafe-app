package pe.com.sinergiacafe.clientes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.clientes.dto.ClienteRequestDTO;
import pe.com.sinergiacafe.clientes.dto.ClienteResponseDTO;
import pe.com.sinergiacafe.clientes.dto.TipoClienteResponseDTO;
import pe.com.sinergiacafe.clientes.entity.ClienteEntity;
import pe.com.sinergiacafe.clientes.entity.TipoClienteEntity;
import pe.com.sinergiacafe.clientes.repository.ClienteRepository;
import pe.com.sinergiacafe.clientes.repository.TipoClienteRepository;
import pe.com.sinergiacafe.clientes.shared.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final TipoClienteRepository tipoClienteRepository;

    @Override
    public List<ClienteResponseDTO> listarTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public ClienteResponseDTO obtenerPorId(Long id) {
        ClienteEntity cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", id));
        return toResponseDTO(cliente);
    }

    @Override
    public ClienteResponseDTO obtenerPorDni(String dni) {
        ClienteEntity cliente = clienteRepository.findByDni(dni)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente con DNI " + dni + " no encontrado"));
        return toResponseDTO(cliente);
    }

    @Override
    public ClienteResponseDTO crear(ClienteRequestDTO request) {
        TipoClienteEntity tipoCliente = tipoClienteRepository.findById(request.getIdTipoCliente())
                .orElseThrow(() -> new ResourceNotFoundException("TipoCliente", request.getIdTipoCliente()));

        ClienteEntity cliente = ClienteEntity.builder()
                .tipoCliente(tipoCliente)
                .nombres(request.getNombres())
                .apellidos(request.getApellidos())
                .dni(request.getDni())
                .telefono(request.getTelefono())
                .correo(request.getCorreo())
                .fechaNacimiento(request.getFechaNacimiento())
                .build();

        return toResponseDTO(clienteRepository.save(cliente));
    }

    @Override
    public ClienteResponseDTO actualizar(Long id, ClienteRequestDTO request) {
        ClienteEntity cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", id));

        TipoClienteEntity tipoCliente = tipoClienteRepository.findById(request.getIdTipoCliente())
                .orElseThrow(() -> new ResourceNotFoundException("TipoCliente", request.getIdTipoCliente()));

        cliente.setTipoCliente(tipoCliente);
        cliente.setNombres(request.getNombres());
        cliente.setApellidos(request.getApellidos());
        cliente.setDni(request.getDni());
        cliente.setTelefono(request.getTelefono());
        cliente.setCorreo(request.getCorreo());
        cliente.setFechaNacimiento(request.getFechaNacimiento());

        return toResponseDTO(clienteRepository.save(cliente));
    }

    @Override
    public void eliminar(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente", id);
        }
        clienteRepository.deleteById(id);
    }

    private ClienteResponseDTO toResponseDTO(ClienteEntity entity) {
        return ClienteResponseDTO.builder()
                .idCliente(entity.getIdCliente())
                .tipoCliente(TipoClienteResponseDTO.builder()
                        .idTipoCliente(entity.getTipoCliente().getIdTipoCliente())
                        .nombre(entity.getTipoCliente().getNombre())
                        .descripcion(entity.getTipoCliente().getDescripcion())
                        .descuentoPct(entity.getTipoCliente().getDescuentoPct())
                        .activo(entity.getTipoCliente().getActivo())
                        .build())
                .nombres(entity.getNombres())
                .apellidos(entity.getApellidos())
                .dni(entity.getDni())
                .telefono(entity.getTelefono())
                .correo(entity.getCorreo())
                .fechaNacimiento(entity.getFechaNacimiento())
                .fechaRegistro(entity.getFechaRegistro())
                .activo(entity.getActivo())
                .build();
    }
}