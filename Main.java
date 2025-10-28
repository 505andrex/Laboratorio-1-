import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase principal para probar el registro de productos.
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ProductoAlimenticio nuevoAlimento = null;

        try {
            System.out.println("--- Registro de Nuevo Producto Alimenticio ---");

            System.out.print("Ingrese Código: ");
            String codigo = scanner.nextLine();

            System.out.print("Ingrese Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Ingrese Precio: ");
            double precio = scanner.nextDouble(); // Puede lanzar InputMismatchException
            scanner.nextLine(); // Limpiar el buffer del scanner

            System.out.print("Ingrese Fecha Caducidad (YYYY-MM-DD): ");
            String fecha = scanner.nextLine();

            // --- Instanciación y Validación ---
            // Aquí se pueden lanzar IllegalArgumentException (del constructor de Producto)
            // o InventarioException (del constructor de ProductoAlimenticio)
            nuevoAlimento = new ProductoAlimenticio(codigo, nombre, precio, fecha);

            // Si todo fue exitoso
            System.out.println("\n--- ¡Producto Registrado Exitosamente! ---");
            System.out.println("Código: " + nuevoAlimento.getCodigo());
            System.out.println("Nombre: " + nuevoAlimento.getNombre());
            System.out.println("Precio: " + nuevoAlimento.getPrecio());
            System.out.println("Caducidad: " + nuevoAlimento.getFechaCaducidad());


        } catch (InventarioException e) {
            // Error de negocio (Checked) - Ej. Producto caducado
            System.err.println("Error de Inventario: " + e.getMessage());

        } catch (IllegalArgumentException e) {
            // Error de validación (Unchecked) - Ej. Precio negativo, nombre vacío
            System.err.println("Error de Validación: " + e.getMessage());

        } catch (InputMismatchException e) {
            // Error de tipo de entrada del usuario - Ej. Escribir "ABC" en el precio
            System.err.println("Error de Entrada: El precio debe ser un número válido.");
            
        } catch (Exception e) {
            // Captura genérica para cualquier otro error inesperado
            System.err.println("Error Inesperado: " + e.getMessage());
            e.printStackTrace(); // Muestra más detalle del error inesperado

        } finally {
            // Asegura que el scanner se cierre siempre
            if (scanner != null) {
                scanner.close();
                System.out.println("\n(Scanner cerrado)");
            }
        }

        // Mostrar el contador total de productos creados, independientemente del resultado
        System.out.println("==========================================");
        System.out.println("Total de productos creados (global): " + Producto.getContadorProductos());
    }
}