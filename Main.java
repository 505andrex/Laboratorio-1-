import java.util.ArrayList; // Necesario para guardar los productos
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase principal para probar el registro de productos.
 */
public class Main {

    // Scanner estático para que todos los métodos lo usen
    private static Scanner scanner = new Scanner(System.in);
    // Lista estática para guardar los productos creados (nuestro inventario)
    private static ArrayList<Producto> inventario = new ArrayList<>();

    public static void main(String[] args) {

        int opcion = 0; // Inicializar opción
        
        do {
            System.out.println("\n*******************************************");
            System.out.println("           MENÚ DE INVENTARIO            ");
            System.out.println("*******************************************");
            System.out.println("1. Registrar producto alimenticio");
            System.out.println("2. Registrar producto electrónico");
            System.out.println("3. Vender Producto");
            System.out.println("4. Mostrar Inventario"); // Añadí esta opción, es muy útil
            System.out.println("5. Salir");
            System.out.print("Elija una opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer (importante después de nextInt())

                switch (opcion) {
                    case 1:
                        registrarProductoAlimenticio();
                        break;
                    case 2:
                        registrarProductoElectronico();
                        break;
                    case 3:
                        venderProducto();
                        break;
                    case 4:
                        mostrarInventario();
                        break;
                    case 5:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.err.println("Opción no válida. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Error: Debe ingresar un número.");
                scanner.nextLine(); // Limpiar la entrada incorrecta
                opcion = 0; // Reiniciar la opción para que el bucle continúe
            }

        } while (opcion != 5); // El bucle termina cuando la opción es 5

        // Asegura que el scanner se cierre siempre al salir
        if (scanner != null) {
            scanner.close();
            System.out.println("\n(Scanner cerrado)");
        }

        // Mostrar el contador total de productos creados, independientemente del resultado
        System.out.println("==========================================");
        System.out.println("Total de productos creados (global): " + Producto.getContadorProductos());
    }

    /**
     * Función para registrar un Producto Alimenticio
     */
    public static void registrarProductoAlimenticio() {
        System.out.println("\n--- Registro de Nuevo Producto Alimenticio ---");
        ProductoAlimenticio nuevoAlimento = new ProductoAlimenticio("", "", 0, 0, "");
        try {
            System.out.print("Ingrese Código: ");
            nuevoAlimento.setCodigo(scanner.nextLine());
            System.out.print("Ingrese Nombre: ");
            nuevoAlimento.setNombre(scanner.nextLine());
            System.out.print("Ingrese Precio: ");
            nuevoAlimento.setPrecio(scanner.nextDouble());
            System.out.print("Ingrese Stock: ");
            nuevoAlimento.setStock(scanner.nextInt());
            scanner.nextLine(); // Limpiar buffer
            System.out.print("Ingrese Fecha Caducidad (YYYY-MM-DD): ");
            nuevoAlimento.setFechaCaducidad(scanner.nextLine());

            // Si todo fue exitoso, lo añadimos al inventario
            inventario.add(nuevoAlimento);

            System.out.println("\n--- ¡Producto Registrado Exitosamente! ---");
            System.out.println("Nombre: " + nuevoAlimento.getNombre() + " (Stock: " + nuevoAlimento.getStock() + ")");

        } catch (InventarioException e) {
            // Error de negocio (Checked) - Ej. Producto caducado
            System.err.println("Error de Inventario: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            // Error de validación (Unchecked) - Ej. Precio negativo, nombre vacío
            System.err.println("Error de Validación: " + e.getMessage());
        } catch (InputMismatchException e) {
            // Error de tipo de entrada del usuario - Ej. Escribir "ABC" en el precio
            System.err.println("Error de Entrada: El precio y el stock deben ser números válidos.");
            scanner.nextLine(); // Limpiar buffer
        } catch (Exception e) {
            // Captura genérica para cualquier otro error inesperado
            System.err.println("Error Inesperado: " + e.getMessage());
        }
    }

    /**
     * Función para registrar un Producto Electrónico
     */
    public static void registrarProductoElectronico() {
        ProductoElectronico nuevoElectronico = new ProductoElectronico("", "", 0, 0, 0);
        System.out.println("\n--- Registro de Nuevo Producto Electrónico ---");
        try {
            System.out.print("Ingrese Código: ");
            nuevoElectronico.setCodigo(scanner.nextLine());
            System.out.print("Ingrese Nombre: ");
            nuevoElectronico.setNombre(scanner.nextLine());
            System.out.print("Ingrese Precio: ");
            nuevoElectronico.setPrecio(scanner.nextDouble());
            System.out.print("Ingrese Stock: ");
            nuevoElectronico.setStock(scanner.nextInt());
            System.out.print("Ingrese Meses de Garantía: ");
            nuevoElectronico.setMesesGarantia(scanner.nextInt());
            scanner.nextLine(); // Limpiar buffer

            // Si todo fue exitoso, lo añadimos al inventario
            inventario.add(nuevoElectronico);

            System.out.println("\n--- ¡Producto Registrado Exitosamente! ---");

        } catch (IllegalArgumentException e) {
            // Error de validación (Unchecked) - Ej. Precio negativo
            System.err.println("Error de Validación: " + e.getMessage());
        } catch (InputMismatchException e) {
            // Error de tipo de entrada del usuario
            System.err.println("Error de Entrada: Precio, stock o garantía deben ser números válidos.");
            scanner.nextLine(); // Limpiar buffer
        } catch (Exception e) {
            // Captura genérica
            System.err.println("Error Inesperado: " + e.getMessage());
        }
    }

    /**
     * Función para vender un producto existente en el inventario
     */
    public static void venderProducto() {
        System.out.println("\n--- Vender Producto ---");
        
        if (inventario.isEmpty()) {
            System.err.println("No hay productos en el inventario para vender.");
            return; // Salir de la función si no hay nada
        }

        // No necesitamos preguntar el TIPO, solo el CÓDIGO.
        System.out.print("Ingrese el CÓDIGO del producto a vender: ");
        String codigoBuscado = scanner.nextLine();

        // 1. Buscar el producto en el inventario
        Producto productoAVender = null;
        for (Producto p : inventario) {
            if (p.getCodigo().equalsIgnoreCase(codigoBuscado)) {
                productoAVender = p;
                break; // Producto encontrado
            }
        }

        // 2. Validar si se encontró
        if (productoAVender == null) {
            System.err.println("Error: Producto con código '" + codigoBuscado + "' no encontrado.");
            return; // Salir de la función
        }

        // 3. Si se encontró, pedir cantidad y vender
        try {
            System.out.println("Producto encontrado: " + productoAVender.getNombre() + " (Stock actual: " + productoAVender.getStock() + ")");
            System.out.print("Ingrese la cantidad a vender: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            // 4. Intentar la venta (esto puede lanzar excepciones)
            productoAVender.vender(cantidad);

            System.out.println("\n¡Venta exitosa!");
            System.out.println("Nuevo stock de " + productoAVender.getNombre() + ": " + productoAVender.getStock());

        } catch (StockInsuficienteException e) {
            System.err.println("Error en la Venta: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error de Cantidad: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.err.println("Error de Entrada: La cantidad debe ser un número entero.");
            scanner.nextLine(); // Limpiar buffer
        }
    }
    
    /**
     * Función (Extra) para mostrar todos los productos en el inventario
     */
    public static void mostrarInventario() {
        System.out.println("\n--- Inventario Actual ---");
        if (inventario.isEmpty()) {
            System.out.println("El inventario está vacío.");
            return;
        }
        
        for (Producto p : inventario) {
            System.out.println("-------------------------");
            System.out.println(" Código: " + p.getCodigo());
            System.out.println(" Nombre: " + p.getNombre());
            System.out.println(" Precio: $" + p.getPrecio());
            System.out.println(" Stock: " + p.getStock());
            
            // Mostrar detalles específicos de cada tipo
            if (p instanceof ProductoAlimenticio) {
                String fecha = ((ProductoAlimenticio) p).getFechaCaducidad();
                System.out.println(" Tipo: Alimenticio");
                System.out.println(" Caducidad: " + fecha);
            } else if (p instanceof ProductoElectronico) {
                int garantia = ((ProductoElectronico) p).getMesesGarantia();
                System.out.println(" Tipo: Electrónico");
                System.out.println(" Garantía: " + garantia + " meses");
            }
        }
        System.out.println("-------------------------");
    }
}