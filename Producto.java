/**
 * Clase abstracta que representa un producto genérico en el inventario.
 * Contiene los atributos y métodos comunes a todos los productos.
 */
public abstract class Producto {

    private String codigo;
    private String nombre;
    private double precio;
    private int stock; //parte del laboratorio 2
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
     * @param stock El stock del comercio - Parte del Labotorio 2
     * @throws IllegalArgumentException Si el nombre, código o precio son inválidos.
     */
    public Producto(String codigo, String nombre, double precio, int stock) {
        this.codigo=codigo;
        this.nombre=nombre;
        this.precio=precio;
        this.stock=stock;
        contadorProductos++; 
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
    /**
     * Parte del Labotorio 2
     * Obtiene el precio del producto.
     * @return 
     */
    public int getStock() {
        return stock;
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

    /**
     * Parte del Labotorio 2
     * @param stock
     * @param IllegalArgumentException
     * @throws 
     */
    public void setStock(int stock) {
        if (stock <0) {
            throw new IllegalArgumentException("No tiene producto en el stock");
        }
        this.stock = stock;
    }

    // --- Métodos de Información ---
    /**
     * Parte del Labotorio 2
     * sirve para vender un producto que esta en stock
     * @param cantidad
     * @throws IllegalArgumentException
     * @throws StockInsuficienteException
     */
    public void vender(int cantidad) throws StockInsuficienteException{
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a vender debe ser positiva");
        }else if(cantidad>stock){
            throw new StockInsuficienteException("Stock insuficiente para "+this.nombre+" disponible "+this.stock);
        }else{
            this.stock -=cantidad;
        }
    }

    /**
     * Devuelve un String con la información base del producto.
     * Método protegido para ser usado por las clases hijas.
     */
    public void mostrarInfoCompleja() {
        System.out.println("**********************************************");
        System.out.println("-----DETALLE PRODUCTO-----");
        System.out.println("Codigo: "+this.codigo);
        System.out.println("Nombre: "+this.codigo);
        if(precio>1000){
            System.out.println("Precio alto "+this.precio);
        }else if (precio>50 && this.precio<=100){
            System.out.println("Precio medio: "+this.precio);
        }else{
            System.out.println("Precio bajo: "+this.precio);
        }
        System.out.println("**********************************************");
    }
}