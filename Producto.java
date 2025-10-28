/**
 * Clase abstracta que representa un producto genérico en el inventario.
 * Contiene los atributos y métodos comunes a todos los productos.
 */
public abstract class Producto {

    private String codigo;
    private String nombre;
    private double precio;

    /**
     * Contador estático para rastrear el número total de productos creados.
     */
    private static int contadorProductos = 0;

    /**
     * Constructor para la clase Producto.
     * Incrementa el contador de productos y valida los datos iniciales.
     *
     * @param codigo El código único del producto.
     * @param nombre El nombre del producto.
     * @param precio El precio del producto.
     * @throws IllegalArgumentException Si el nombre, código o precio son inválidos.
     */
    public Producto(String codigo, String nombre, double precio) {
        // Usamos los setters para asegurar la validación al construir
        this.setCodigo(codigo);
        this.setNombre(nombre);
        this.setPrecio(precio);
        contadorProductos++; // Incrementa el contador
    }

    // --- Getters ---

    /**
     * Obtiene el contador total de productos instanciados.
     * @return El número total de productos.
     */
    public static int getContadorProductos() {
        return contadorProductos;
    }
    /**
     * Obtiene el código del producto.
     * @return El código.
     */
    public String getCodigo() {
        return codigo;
    }
    /**
     * Obtiene el nombre del producto.
     * @return El nombre.
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Obtiene el precio del producto.
     * @return El precio.
     */
    public double getPrecio() {
        return precio;
    }



    // --- Setters con Validación ---

    /**
     * Establece el precio del producto.
     *
     * @param precio El nuevo precio.
     * @throws IllegalArgumentException Si el precio es negativo.
     */
    public void setPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.precio = precio;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param nombre El nuevo nombre.
     * @throws IllegalArgumentException Si el nombre es nulo o vacío.
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es requerido.");
        }
        this.nombre = nombre;
    }

    /**
     * Establece el código del producto.
     *
     * @param codigo El nuevo código.
     * @throws IllegalArgumentException Si el código es nulo o vacío.
     */
    public void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El codigo es requerido.");
        }
        this.codigo = codigo;
    }
    
    // --- Métodos de Información ---

    /**
     * Devuelve un String con la información base del producto.
     * Método protegido para ser usado por las clases hijas.
     *
     * @return Detalles base (código, nombre, precio).
     */
    protected String getDetalleBase() {
        return "Código: " + this.codigo +
               "\nNombre: " + this.nombre +
               "\nPrecio: S/ " + this.precio;
    }
}