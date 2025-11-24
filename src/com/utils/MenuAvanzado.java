package com.utils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Clase utilitaria para la creación de menús en consola con soporte para acciones
 * asociadas a cada opción. Permite reutilizar menús en múltiples programas y facilita
 * la implementación de submenús.
 */
public class MenuAvanzado {

    /**
     * Scanner utilizado para leer la entrada del usuario desde la consola.
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Representa una opción de menú con su descripción y la acción que se ejecuta
     * al seleccionarla.
     */
    public static class OpcionMenu {

        /**
         * Descripción de la opción que se mostrará en pantalla.
         */
        private final String descripcion;

        /**
         * Acción a ejecutar cuando el usuario selecciona esta opción.
         */
        private final Runnable accion;

        /**
         * Constructor de una opción de menú.
         *
         * @param descripcion Texto que describe la opción.
         * @param accion      Acción a ejecutar al seleccionar la opción.
         */
        public OpcionMenu(String descripcion, Runnable accion) {
            this.descripcion = descripcion;
            this.accion = accion;
        }

        /**
         * Obtiene la descripción de la opción de menú.
         *
         * @return La descripción de la opción.
         */
        public String getDescripcion() {
            return descripcion;
        }

        /**
         * Ejecuta la acción asociada a esta opción de menú.
         */
        public void ejecutar() {
            accion.run();
        }
    }

    /**
     * Muestra un menú en la consola y gestiona la selección del usuario.
     * Cada opción del menú está asociada a una acción que se ejecuta cuando
     * el usuario la selecciona.
     *
     * @param titulo   Título que se mostrará en la parte superior del menú.
     * @param opciones Mapa que asocia números (opciones) a objetos {@link OpcionMenu}.
     *
     * <p>El método valida la entrada del usuario y vuelve a solicitarla si no es
     * válida o no corresponde a ninguna opción.</p>
     *
     * <p>Ejemplo de uso:</p>
     * <pre>{@code
     * LinkedHashMap<Integer, MenuAvanzado.OpcionMenu> menu = new LinkedHashMap<>();
     * menu.put(1, new MenuAvanzado.OpcionMenu("Decir Hola", () -> System.out.println("Hola!")));
     * menu.put(2, new MenuAvanzado.OpcionMenu("Salir", () -> System.exit(0)));
     * MenuAvanzado.mostrarMenu("Menú Principal", menu);
     * }</pre>
     */
    public static void mostrarMenu(String titulo, LinkedHashMap<Integer, OpcionMenu> opciones) {
        while (true) {
            System.out.println("\n=== " + titulo + " ===");
            for (Map.Entry<Integer, OpcionMenu> entry : opciones.entrySet()) {
                System.out.println(entry.getKey() + ". " + entry.getValue().getDescripcion());
            }
            System.out.print("Selecciona una opción: ");

            try {
                int seleccion = Integer.parseInt(scanner.nextLine());
                if (opciones.containsKey(seleccion)) {
                    opciones.get(seleccion).ejecutar();
                    break; // Sale del menú si la acción no repite el menú
                } else {
                    System.out.println("Opción no válida. Intenta de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debes introducir un número.");
            }
        }
    }
}
