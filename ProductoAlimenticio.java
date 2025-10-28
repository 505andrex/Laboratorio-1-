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
     * @param fechaCaducidad La fecha de caducidad (formato "YYYY-MM-DD").
     * @throws IllegalArgumentException Si los datos base son inválidos.
     * @throws InventarioException Si la fecha de caducidad es inválida (producto caducado).
     */
    public ProductoAlimenticio(String codigo, String nombre, double precio, String fechaCaducidad)
            throws InventarioException {
        super(codigo, nombre, precio);
        this.setFechaCaducidad(fechaCaducidad); // Valida y asigna la fecha
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
     * Realiza una simulación de validación de fecha.
     *
     * @param fechaCaducidad La fecha en formato "YYYY-MM-DD".
     * @throws InventarioException Si la fecha es "2024-01-01" (simulando caducidad).
     */
    public void setFechaCaducidad(String fechaCaducidad) throws InventarioException {
        // Simulación de validación de fecha pasada
        if ("2024-01-01".equals(fechaCaducidad)) {
            throw new InventarioException("El producto está caducado (Fecha simulada: 2024-01-01).");
        }
        
        // Aquí iría una validación de formato real (omitida por simplicidad)
        
        this.fechaCaducidad = fechaCaducidad;
    }
}  