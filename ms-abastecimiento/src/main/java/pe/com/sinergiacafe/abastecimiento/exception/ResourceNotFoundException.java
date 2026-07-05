package pe.com.sinergiacafe.abastecimiento.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String entity, Long id) {
        super(entity + " con ID " + id + " no encontrado");
    }
}