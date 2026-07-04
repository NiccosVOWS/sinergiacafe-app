package pe.com.sinergiacafe.administracion.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.sinergiacafe.administracion.dto.DocumentoAdministrativoRequestDTO;
import pe.com.sinergiacafe.administracion.dto.DocumentoAdministrativoResponseDTO;
import pe.com.sinergiacafe.administracion.entity.DocumentoAdministrativoEntity;
import pe.com.sinergiacafe.administracion.repository.DocumentoAdministrativoRepository;
import pe.com.sinergiacafe.administracion.service.DocumentoAdministrativoService;
import pe.com.sinergiacafe.administracion.shared.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentoAdministrativoServiceImpl implements DocumentoAdministrativoService {

    private final DocumentoAdministrativoRepository documentoAdministrativoRepository;

    @Override
    public List<DocumentoAdministrativoResponseDTO> listarTodos() {
        return documentoAdministrativoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public DocumentoAdministrativoResponseDTO obtenerPorId(Long id) {
        DocumentoAdministrativoEntity documento = documentoAdministrativoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Documento Administrativo", id));

        return toResponseDTO(documento);
    }

    @Override
    public DocumentoAdministrativoResponseDTO crear(DocumentoAdministrativoRequestDTO request) {

        DocumentoAdministrativoEntity documento = DocumentoAdministrativoEntity.builder()
                .tipoDocumento(request.getTipoDocumento())
                .fechaDocumento(request.getFechaDocumento())
                .descripcion(request.getDescripcion())
                .idEmpleado(request.getIdEmpleado())
                .build();

        return toResponseDTO(documentoAdministrativoRepository.save(documento));
    }

    @Override
    public DocumentoAdministrativoResponseDTO actualizar(Long id, DocumentoAdministrativoRequestDTO request) {

        DocumentoAdministrativoEntity documento = documentoAdministrativoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Documento Administrativo", id));

        documento.setTipoDocumento(request.getTipoDocumento());
        documento.setFechaDocumento(request.getFechaDocumento());
        documento.setDescripcion(request.getDescripcion());
        documento.setIdEmpleado(request.getIdEmpleado());

        return toResponseDTO(documentoAdministrativoRepository.save(documento));
    }

    @Override
    public void eliminar(Long id) {

        if (!documentoAdministrativoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Documento Administrativo", id);
        }

        documentoAdministrativoRepository.deleteById(id);
    }

    private DocumentoAdministrativoResponseDTO toResponseDTO(DocumentoAdministrativoEntity entity) {

        return DocumentoAdministrativoResponseDTO.builder()
                .idDocumentoAdministrativo(entity.getIdDocumentoAdministrativo())
                .tipoDocumento(entity.getTipoDocumento())
                .fechaDocumento(entity.getFechaDocumento())
                .descripcion(entity.getDescripcion())
                .idEmpleado(entity.getIdEmpleado())
                .build();
    }
}
