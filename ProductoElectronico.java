/**
 * Representa un producto electrónico, que es un tipo de Producto.
 * Añade la gestión de los meses de garantía.
 */
public class ProductoElectronico extends Producto {

    private int mesesGarantia;

    /**
     * Constructor para ProductoElectronico.
     *
     * @param codigo El código único.
     * @param nombre El nombre.
     * @param precio El precio.
     * @param stock El stock - parte del laboratorio 2
     * @param mesesGarantia Los meses de garantía (no puede ser negativo).
     * @throws IllegalArgumentException Si los datos base o los meses de garantía son inválidos.
     */
    public ProductoElectronico(String codigo, String nombre, double precio, int stock, int mesesGarantia){
        super(codigo, nombre, precio, stock);
        this.mesesGarantia=mesesGarantia;
    }

     // --- Getter ---
    /**
     * Obtiene los meses de garantía.
     * @return Los meses de garantía.
     */
    public int getMesesGarantia() {
        return mesesGarantia;
    }

    // --- Setters con Validación ---
    /**
     * Establece los meses de garantía.
     *
     * @param mesesGarantia El número de meses.
     * @throws IllegalArgumentException Si los meses son negativos.
     */
    public void setMesesGarantia(int mesesGarantia) {
        if (mesesGarantia < 0) {
            throw new IllegalArgumentException("Los meses de garantía no pueden ser negativos.");
        }
        this.mesesGarantia = mesesGarantia;
    }
}