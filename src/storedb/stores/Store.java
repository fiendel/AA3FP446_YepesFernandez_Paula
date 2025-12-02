package storedb.stores;
import com.qol.*;
import storedb.products.*;

import java.util.LinkedList;
import java.util.List;

public class Store {

    private  int id;
    private String nombre;
    private Producto producto;
    private List<Producto> almacen = new LinkedList<Producto>();
    private IO io = new IO();

    public Store(int id, String nombre){
        this.nombre = nombre;
        this.id = id;
    }


    public Store(){

    }

    public int getId() {
        return id;
    }

    public String getNombre(){
        return nombre;
    }
    public void addProduct(Producto producto){
        almacen.add(producto);

    }



    public void getProduct(){
        for (Producto p : almacen){
           io.println("ID: " + p.getId() + " Type: " + p.getType() +" Description: " +  p.getDescription());
        }
    }

    public void consultarStock(int producto){
        for (Producto p : almacen){
            if(p.getId() == producto){
                io.println(p.getStock() + "");
            }
        }
    }

    public List<Producto> getAlmacen() {
        return almacen;
    }

    public void setAlmacen(List<Producto> almacen) {
        this.almacen = almacen;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Store(id:%d, name:%s)%n", id, nombre));
        return sb.toString();
    }
}
