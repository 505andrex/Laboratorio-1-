import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Representa un producto alimenticio, que es un tipo de Producto.
 * Añade la gestión de la fecha de caducidad.
 */
public class ProductoAlimenticio extends Producto {

    private String fechaCaducidad; // Formato "YYYY-MM-DD"

    /**
     * Constructor para ProductoAlimenticio.
     *
     * @param codigo El código único.
     * @param nombre El nombre.
     * @param precio El precio.
     * @param stock El stock de productos
     * @param fechaCaducidad La fecha de caducidad (formato "YYYY-MM-DD").
     */
    public ProductoAlimenticio(String codigo, String nombre, double precio, int stock, String fechaCaducidad){
        super(codigo, nombre, precio, stock);
        this.fechaCaducidad=fechaCaducidad;
    }

    /**
     * Obtiene la fecha de caducidad.
     * @return La fecha de caducidad.
     */
    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

/**
     * Establece la fecha de caducidad.
     * Valida que la fecha tenga el formato "YYYY-MM-DD" y que no sea anterior
     * a la fecha actual del sistema.
     *
     * @param fechaCaducidad La fecha en formato "YYYY-MM-DD".
     * @throws InventarioException Si la fecha de caducidad es una fecha ya pasada.
     * @throws IllegalArgumentException Si el formato de la fecha es inválido (diferente de "YYYY-MM-DD").
     */
    public void setFechaCaducidad(String fechaCaducidad) throws InventarioException {
        
        LocalDate fechaCad;
        LocalDate hoy = LocalDate.now();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            
            fechaCad = LocalDate.parse(fechaCaducidad, formatter);

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha inválido. Se esperaba YYYY-MM-DD.", e);
        }
        if (fechaCad.isBefore(hoy)) {
            throw new InventarioException("El producto está caducado. Fecha de caducidad: " + fechaCaducidad);
        }
        
        this.fechaCaducidad = fechaCaducidad;
    }
}  