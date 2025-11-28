package com.qol;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class IO {

    private final Scanner scanner = new Scanner(System.in);



    public boolean println(String string) {
        try {
            System.out.println(string != null ? string : "null");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean print(String string) {
        try {
            System.out.print(string != null ? string : "null");
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public String readln() {
        try {
            String result = scanner.nextLine();
            return result != null ? result : "";
        } catch (Exception e) {
            scannerReset();
            return "";
        }
    }

    public int readInt() {
        while (true) {
            try {
                int value = scanner.nextInt();
                scanner.nextLine(); // clean buffer
                return value;
            } catch (InputMismatchException e) {
                println("Invalid number. Try again:");
                scanner.nextLine(); // discard invalid token
            } catch (Exception e) {
                scannerReset();
                return 0;
            }
        }
    }

    public float readFloat() {
        while (true) {
            try {
                float value = scanner.nextFloat();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                println("Invalid number. Try again:");
                scanner.nextLine();
            } catch (Exception e) {
                scannerReset();
                return 0.0f;
            }
        }
    }

    public double readDouble() {
        while (true) {
            try {
                double value = scanner.nextDouble();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                println("Invalid number. Try again:");
                scanner.nextLine();
            } catch (Exception e) {
                scannerReset();
                return 0.0;
            }
        }
    }

    public boolean readBool() {
        while (true) {
            try {
                boolean value = scanner.nextBoolean();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                println("Invalid boolean. Use true/false. Try again:");
                scanner.nextLine();
            } catch (Exception e) {
                scannerReset();
                return false;
            }
        }
    }

    public int read() {
        try {
            return System.in.read();
        } catch (Exception e) {
            return -1;
        }
    }



    public ArrayList<String> ParserNombre(String nombreRaw) {
        ArrayList<String> listaFinal = new ArrayList<>();

        if (nombreRaw == null || nombreRaw.trim().isEmpty())
            return listaFinal;

        try {
            String[] parsed = nombreRaw.split("\\s+");
            for (String palabra : parsed) {
                if (palabra != null && !palabra.isEmpty()) {
                    listaFinal.add(palabra);
                }
            }
        } catch (Exception e) {
            // return empty list on failure
        }
        return listaFinal;
    }



    /** Resets scanner if it becomes corrupted */
    private void scannerReset() {
        try {
            scanner.nextLine();
        } catch (Exception ignored) {}
    }
}
