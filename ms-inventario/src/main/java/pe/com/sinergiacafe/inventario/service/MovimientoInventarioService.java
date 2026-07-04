package pe.com.sinergiacafe.inventario.service;

import pe.com.sinergiacafe.inventario.dto.MovimientoInventarioRequestDTO;
import pe.com.sinergiacafe.inventario.dto.MovimientoInventarioResponseDTO;
import pe.com.sinergiacafe.inventario.entity.MovimientoInventarioEntity;
import pe.com.sinergiacafe.inventario.exception.ResourceNotFoundException;
import pe.com.sinergiacafe.inventario.repository.MovimientoInventarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimientoInventarioService {

    private final MovimientoInventarioRepository repository;

    public List<MovimientoInventarioResponseDTO> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public MovimientoInventarioResponseDTO findById(Long id) {
        MovimientoInventarioEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MovimientoInventario no encontrado con id: " + id));
        return toResponse(entity);
    }

    public MovimientoInventarioResponseDTO create(MovimientoInventarioRequestDTO dto) {
        MovimientoInventarioEntity entity = new MovimientoInventarioEntity();
        entity.setInsumoId(dto.getInsumoId());
        entity.setTipo(dto.getTipo());
        entity.setCantidad(dto.getCantidad());
        entity.setMotivo(dto.getMotivo());
        entity.setFecha(dto.getFecha());
        return toResponse(repository.save(entity));
    }

    public MovimientoInventarioResponseDTO update(Long id, MovimientoInventarioRequestDTO dto) {
        MovimientoInventarioEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MovimientoInventario no encontrado con id: " + id));
        entity.setInsumoId(dto.getInsumoId());
        entity.setTipo(dto.getTipo());
        entity.setCantidad(dto.getCantidad());
        entity.setMotivo(dto.getMotivo());
        entity.setFecha(dto.getFecha());
        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("MovimientoInventario no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }

    private MovimientoInventarioResponseDTO toResponse(MovimientoInventarioEntity entity) {
        return MovimientoInventarioResponseDTO.builder()
                .id(entity.getId())
                .insumoId(entity.getInsumoId())
                .tipo(entity.getTipo())
                .cantidad(entity.getCantidad())
                .motivo(entity.getMotivo())
                .fecha(entity.getFecha())
                .build();
    }
}
