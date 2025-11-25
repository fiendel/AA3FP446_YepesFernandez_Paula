package storedb.products.Types;
import storedb.products.Producto;
import storedb.products.Types.Subclasses.TipoTablero;
import storedb.providers.Provider;

public class Tablero extends Producto {
    private double height;
    private  double width;
    TipoTablero tipoTablero;


    public Tablero(int id, String description, Provider provider,
                   int stock, double price, double height,
                   TipoTablero tipoTablero, double width)
    {
        super(id, description, provider, stock, price);
        this.height = height;
        this.tipoTablero = tipoTablero;
        this.width = width;
    }

    public Tablero(){

    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public TipoTablero getTipoTablero() {
        return tipoTablero;
    }

    public void setTipoTablero(TipoTablero tipoTablero) {
        this.tipoTablero = tipoTablero;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}


