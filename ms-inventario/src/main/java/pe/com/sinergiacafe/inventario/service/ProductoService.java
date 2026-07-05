package pe.com.sinergiacafe.inventario.service;

import pe.com.sinergiacafe.inventario.dto.ProductoRequestDTO;
import pe.com.sinergiacafe.inventario.dto.ProductoResponseDTO;

import java.util.List;

public interface ProductoService {

    List<ProductoResponseDTO> listarTodos();
    ProductoResponseDTO obtenerPorId(Long id);
    ProductoResponseDTO crear(ProductoRequestDTO request);
    ProductoResponseDTO actualizar(Long id, ProductoRequestDTO request);
    void eliminar(Long id);
}