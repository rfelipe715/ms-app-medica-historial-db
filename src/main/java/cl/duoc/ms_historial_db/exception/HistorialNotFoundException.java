package cl.duoc.ms_historial_db.exception;

public class HistorialNotFoundException extends RuntimeException {

    public HistorialNotFoundException(Long id) {
        super("Historial no encontrado con el id: " + id);
    }
}
