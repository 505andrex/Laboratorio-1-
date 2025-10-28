/**
 * Excepción personalizada para errores de lógica de negocio en el inventario.
 * Es una 'checked exception', por lo que debe ser declarada o manejada.
 */
public class InventarioException extends Exception {

    /**
     * Constructor que recibe el mensaje de error.
     *
     * @param mensaje El mensaje detallado del error.
     */
    public InventarioException(String mensaje) {
        super(mensaje);
    }
}