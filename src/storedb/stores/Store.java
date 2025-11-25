package storedb.stores;
import com.qol.*;
import storedb.products.*;

import java.util.LinkedList;
import java.util.List;

public class Store {

    private  int id;
    private String nombre;
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
           io.println(p.getDescription());
        }
    }

    public void consultarStock(int producto){
        for (Producto p : almacen){
            if(p.getId() == producto){
                io.println(p.getStock() + "");
            }
        }
    }
}
