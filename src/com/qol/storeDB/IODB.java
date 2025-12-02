package com.qol.storeDB;
import com.qol.IO;
import storedb.DB;
import java.io.*;
import java.util.ArrayList;

public class IODB extends IO implements Serializable {

    public IODB() {
    }

    //region overrides
    @Override
    public ArrayList<String> ParserNombre(String nombreRaw) {
        return super.ParserNombre(nombreRaw);
    }

    @Override
    public boolean print(String string) {
        return super.print(string);
    }

    @Override
    public boolean println(String string) {
        return super.println(string);
    }

    @Override
    public int read() {
        return super.read();
    }

    @Override
    public boolean readBool() {
        return super.readBool();
    }

    @Override
    public double readDouble() {
        return super.readDouble();
    }

    @Override
    public float readFloat() {
        return super.readFloat();
    }

    @Override
    public int readInt() {
        return super.readInt();
    }

    @Override
    public String readln() {
        return super.readln();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }



    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    //endregion


    // ===================== New DB Save Method =====================
    /**
     * Saves a DB object to a file.
     * @param db the database object to save
     * @param filename path of the file where DB will be saved
     * @return true if successful, false if error occurs
     */
    public boolean saveDBToFile(DB db, String filename) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos))
        {
            oos.writeObject(db);
            println("Database saved successfully to " + filename);
            return true;
        }
        catch (IOException e)
        {
            println("Error saving database: " + e.getMessage());
            return false;
        }
    }

    public DB loadDBFromFile(String filename) {
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            DB db = (DB) ois.readObject();
            println("Database loaded successfully from " + filename);
            return db;
        } catch (IOException | ClassNotFoundException e) {
            println("Error loading database: " + e.getMessage());
            return new DB(); // return empty DB on failure
        }
    }

}