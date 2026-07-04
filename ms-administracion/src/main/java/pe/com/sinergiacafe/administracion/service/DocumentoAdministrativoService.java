package pe.com.sinergiacafe.administracion.service;

import pe.com.sinergiacafe.administracion.dto.DocumentoAdministrativoRequestDTO;
import pe.com.sinergiacafe.administracion.dto.DocumentoAdministrativoResponseDTO;

import java.util.List;

public interface DocumentoAdministrativoService {

    List<DocumentoAdministrativoResponseDTO> listarTodos();

    DocumentoAdministrativoResponseDTO obtenerPorId(Long id);

    DocumentoAdministrativoResponseDTO crear(DocumentoAdministrativoRequestDTO request);

    DocumentoAdministrativoResponseDTO actualizar(Long id, DocumentoAdministrativoRequestDTO request);

    void eliminar(Long id);
}
