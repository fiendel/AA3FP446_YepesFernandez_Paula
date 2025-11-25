package com.qol;
import java.util.ArrayList;
import java.util.Scanner;

public class IO {
    private Scanner scanner = new Scanner(System.in);

    public int println(String string){
        try {
            System.out.println(string);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    public boolean readBool(){
        return scanner.nextBoolean();
    }

    public int print(String string){
        try {
            System.out.print(string);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    public int read(){
        try {
            System.in.read();
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    public String readln (){
        return scanner.nextLine();
    }

    public float readf (){
        return scanner.nextFloat();
    }

    public int readInt(){
        return scanner.nextInt();
    }

    public double readDouble(){
        return scanner.nextDouble();
    }


    public ArrayList<String> ParserNombre(String nombreRaw){
        String regex = "[\\s]";
        String[] parsed = nombreRaw.split(regex);
        ArrayList<String> listaFinal = new ArrayList<>();
        int index = 0;
        for (String palabras : parsed){
            listaFinal.add(parsed[index]);
            index++;

        }
        return listaFinal;

    }
}
