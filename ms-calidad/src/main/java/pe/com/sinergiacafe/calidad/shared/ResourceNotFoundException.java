package pe.com.sinergiacafe.calidad.shared;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String recurso, Long id) {
        super(recurso + " con ID " + id + " no encontrado");
    }

    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }
}