package storedb.providers;

public class Provider {
    private String nif;
    private String nombre;

    public Provider(String nif, String nombre ){
        setNif(nif);
        setNombre(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    @Override
    public String toString() {
        return String.format("Provider(NIF: %s, Name: %s)", nif, nombre);
    }

}
