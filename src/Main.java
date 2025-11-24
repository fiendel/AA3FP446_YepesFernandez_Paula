import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import storedb.products.*;
import com.utils.*;
import com.qol.*;
import storedb.providers.Provider;
import storedb.stores.Store;

public class Main {
    public static void main(String[] args) {

        boolean salir = false;
        final IO io = new IO();
        List<Store> stores = new LinkedList<Store>();
        Runnable opcionSalir = () -> System.out.println("¡Adiós!");

        Runnable subMenuProductos = () -> {
            LinkedHashMap<Integer, MenuAvanzado.OpcionMenu> subOpciones = new LinkedHashMap<>();
            subOpciones.put(1, new MenuAvanzado.OpcionMenu("Añadir producto", () -> System.out.println("Producto añadido")));
            subOpciones.put(2, new MenuAvanzado.OpcionMenu("Listar productos", () -> System.out.println("Mostrando productos")));
            subOpciones.put(3, new MenuAvanzado.OpcionMenu("Volver al menú principal", () -> {}));

            MenuAvanzado.mostrarMenu("Submenú Productos", subOpciones);
        };

        Runnable addStore = () ->{
            io.print("Insert store's id: ");
            int id = io.readInt();
            io.readln();
            io.print("Insert the store name: ");
            String nombre = io.readln();
            stores.add(new Store(id, nombre));
            io.println("ID: " + id + " Name: " + nombre);
        };

        Runnable getStock = () ->{

            io.println("Insert the id: ");
            int id = io.readInt();

            for(Store store : stores){

                if(store.getId() == id){
                    io.println("" + store.getId());
                }
            }
        };


        Runnable getStores = () ->{

            for(Store store : stores){
                io.println("ID: " + store.getId() + " Name: " + store.getNombre());
            }
        };

        interface Supplier<T> {
            T get(); // Devuelve algo del tipo T
        }

        Supplier addProvider = () ->{
            io.println("Set client NIF: ");
            String nif = io.readln();
            io.println("Set client name: ");
            String name = io.readln();
            Provider provider = new Provider(nif, name);
            io.println(provider.getNif() + provider.getNombre());
            return provider;

        };
        Runnable addProduct = () ->{
            io.println("Set product id: ");
            io.readln();
            int id = io.readInt();
            io.println("Set product description: ");
            String description = io.readln();
            io.println("Set product provider: ");
            Provider provider = (Provider)addProvider.get() ;
            io.println("Set product stock: ");
            int stock = io.readInt();
            io.println("Set product price: ");
            double price;
            io.println("Set product id: ");
            Producto producto = new Producto(id,description,
                    new Provider(provider.getNif(),provider.getNombre()),stock,123);

            io.println(producto.getDescription());
        };

        Runnable  mngStore = () ->{
            LinkedHashMap<Integer, ExtendedMenu.OpcionMenu> subOpciones = new LinkedHashMap<>();
            io.println("Insert the id: ");
            int id = io.readInt();
            io.readln();
            for(Store store : stores){
                if(store.getId() == id){
                    io.println("" + store.getId());
                    subOpciones.put(1, new ExtendedMenu.OpcionMenu("Get name", () -> store.getNombre()));
                    subOpciones.put(2, new ExtendedMenu.OpcionMenu("Get name",  addProduct));

                }
            }
            ExtendedMenu.mostrarMenu("Menú Principal", subOpciones);

        };



        Runnable storesMgr = () ->{
            LinkedHashMap<Integer, ExtendedMenu.OpcionMenu> subOpciones = new LinkedHashMap<>();
            subOpciones.put(1, new ExtendedMenu.OpcionMenu("Add store", addStore));
            subOpciones.put(2, new ExtendedMenu.OpcionMenu("List Stores", getStores));
            subOpciones.put(3, new ExtendedMenu.OpcionMenu("Enter store manager", mngStore));
            io.println("");
            io.println("Provider options: ");
            //subOpciones.put(3, new ExtendedMenu.OpcionMenu("Add provider", addProvider));

            subOpciones.put(4, new ExtendedMenu.OpcionMenu("Get stock", getStock));
            ExtendedMenu.mostrarMenu("Menú Principal", subOpciones);
        };

        Runnable menuEnTest = () -> {

            LinkedHashMap<Integer, MenuAvanzado.OpcionMenu> subOpciones = new LinkedHashMap<>();
            subOpciones.put(1, new MenuAvanzado.OpcionMenu("Listar  producto", () -> io.println("Jaarl")));
            subOpciones.put(2, new MenuAvanzado.OpcionMenu("Stores manager", storesMgr));

            MenuAvanzado.mostrarMenu("Menú Principal", subOpciones);

        };




        while (!salir) {
            LinkedHashMap<Integer, MenuAvanzado.OpcionMenu> menuPrincipal = new LinkedHashMap<>();
            menuPrincipal.put(1, new MenuAvanzado.OpcionMenu("Gestión en test", subMenuProductos));

            menuPrincipal.put(2, new MenuAvanzado.OpcionMenu("Consultar stock", menuEnTest));
            //menuPrincipal.put(3, new MenuAvanzado.OpcionMenu("Salir", () -> salir = true));

            MenuAvanzado.mostrarMenu("Menú Principal", menuPrincipal);
        }
    }
}
