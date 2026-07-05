package pe.com.sinergiacafe.inventario.service;

import pe.com.sinergiacafe.inventario.dto.CategoriaProductoRequestDTO;
import pe.com.sinergiacafe.inventario.dto.CategoriaProductoResponseDTO;

import java.util.List;

public interface CategoriaProductoService {

    List<CategoriaProductoResponseDTO> listarTodos();
    CategoriaProductoResponseDTO obtenerPorId(Long id);
    CategoriaProductoResponseDTO crear(CategoriaProductoRequestDTO request);
    CategoriaProductoResponseDTO actualizar(Long id, CategoriaProductoRequestDTO request);
    void eliminar(Long id);
}