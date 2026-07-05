package pe.com.sinergiacafe.finanzas.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String entity, Long id) {
        super(entity + " con ID " + id + " no encontrado");
    }
}