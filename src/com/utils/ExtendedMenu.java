package com.utils;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ExtendedMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static class OpcionMenu {

        private final String descripcion;
        private final Runnable accion;

        public OpcionMenu(String descripcion, Runnable accion) {
            this.descripcion = descripcion;
            this.accion = accion;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void ejecutar() {
            accion.run();
        }
    }

    public static void mostrarMenu(String titulo, LinkedHashMap<Integer, ExtendedMenu.OpcionMenu> opciones) {
        while (true) {
            System.out.println("\n=== " + titulo + " ===");
            for (Map.Entry<Integer, ExtendedMenu.OpcionMenu> entry : opciones.entrySet()) {
                System.out.println(entry.getKey() + ". " + entry.getValue().getDescripcion());
            }
            System.out.print("Selecciona una opción: ");

            try {
                int seleccion = Integer.parseInt(scanner.nextLine());
                if (opciones.containsKey(seleccion)) {
                    opciones.get(seleccion).ejecutar();
                    break;
                } else {
                    System.out.println("Opción no válida. Intenta de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debes introducir un número.");
            }
        }
    }
}
