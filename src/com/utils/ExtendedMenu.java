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

    // Basic menu (repeats until 0 is selected)
    public static void mostrarMenu(String titulo, LinkedHashMap<Integer, OpcionMenu> opciones) {

        boolean repetir = true;

        while (repetir) {
            System.out.println("\n=== " + titulo + " ===");

            // Print options
            for (Map.Entry<Integer, OpcionMenu> entry : opciones.entrySet()) {
                System.out.println(entry.getKey() + ". " + entry.getValue().getDescripcion());
            }

            // automatic 0 = exit
            System.out.println("0. Go back");
            System.out.print("Select an option: ");

            int seleccion;
            try {
                seleccion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            if (seleccion == 0) {
                repetir = false;
                continue;
            }

            OpcionMenu opcion = opciones.get(seleccion);

            if (opcion != null) {
                opcion.ejecutar();
            } else {
                System.out.println("Invalid selection. Please try again.");
            }
        }
    }

    // Menu with optional single execution
    public static void mostrarMenu(String titulo, LinkedHashMap<Integer, OpcionMenu> opciones, boolean loopActive) {

        do {
            System.out.println("\n=== " + titulo + " ===");

            // Print options
            for (Map.Entry<Integer, OpcionMenu> entry : opciones.entrySet()) {
                System.out.println(entry.getKey() + ". " + entry.getValue().getDescripcion());
            }

            // Automatic 0 = exit
            System.out.println("0. Go back");
            System.out.print("Select an option: ");

            int seleccion;
            try {
                seleccion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            if (seleccion == 0) {
                loopActive = false;
                continue;
            }

            OpcionMenu opcion = opciones.get(seleccion);

            if (opcion != null) {
                opcion.ejecutar();
            } else {
                System.out.println("Invalid selection. Please try again.");
            }

            // Exit after one execution if loopActive is false
            if (!loopActive) {
                loopActive = false;
            }

        } while (loopActive);
    }

}
