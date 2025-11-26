import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import storedb.products.*;
import com.utils.*;
import com.qol.*;
import storedb.products.Types.Barniz;
import storedb.products.Types.Subclasses.ColorBarniz;
import storedb.products.Types.Subclasses.TipoTablero;
import storedb.products.Types.Tablero;
import storedb.providers.Provider;
import storedb.stores.Store;
import storedb.DB;

public class Main {
    public static void main(String[] args) {

        boolean salir = false;
        final IO io = new IO();
        List<Store> stores = new LinkedList<>();
        List<Producto> productos = new LinkedList<>();
        List<Provider> providers = new LinkedList<>();
        DB db = new DB();

        // Interfaces (can be declared inside main)
        interface Supplier<T> { T get(); }
        interface Table<T> { T get(); }
        interface Product<T> { T get(); }
        interface Varnish<T> { T get(); }

        Runnable opcionSalir = () -> System.out.println("¡Adiós!");

        Runnable subMenuProductos = () -> {
            LinkedHashMap<Integer, MenuAvanzado.OpcionMenu> subOpciones = new LinkedHashMap<>();
            subOpciones.put(1, new MenuAvanzado.OpcionMenu("Añadir producto", () -> System.out.println("Producto añadido")));
            subOpciones.put(2, new MenuAvanzado.OpcionMenu("Listar productos", () -> System.out.println("Mostrando productos")));
            subOpciones.put(3, new MenuAvanzado.OpcionMenu("Volver al menú principal", () -> {}));

            MenuAvanzado.mostrarMenu("Submenú Productos", subOpciones);
        };

        Runnable addStore = () -> {
            io.print("Insert store's id: ");
            int id = io.readInt();
            io.readln();
            io.print("Insert the store name: ");
            String nombre = io.readln();
            stores.add(new Store(id, nombre));
            io.println("ID: " + id + " Name: " + nombre);
        };

        Runnable getStock = () -> {
            for (Store store : stores) {
                io.println(store.getId() + " " + store.getNombre());
                for (Producto p : store.getAlmacen()) {
                    io.println(p.getId() + " " + p.getStock());
                }
            }
        };

        Runnable getStores = () -> {
            for (Store store : stores) {
                io.println("ID: " + store.getId() + " Name: " + store.getNombre());
            }
        };

        Supplier<Provider> addProvider = () -> {
            io.println("Set client NIF: ");
            String nif = io.readln();
            io.println("Set client name: ");
            String name = io.readln();
            Provider provider = new Provider(nif, name);
            io.println(provider.getNif() + " " + provider.getNombre());
            return provider;
        };

        Table<Tablero> createTablero = () -> {
            io.println("Set product id: ");
            int id = io.readInt();
            io.readln();
            io.println("Set product description: ");
            String description = io.readln();

            io.println("Set product stock: ");
            int stock = io.readInt();
            io.readln();
            io.println("Set product price: ");
            double price = io.readDouble();
            io.readln();
            io.println("Set product height: ");
            double height = io.readDouble();
            io.readln();
            io.println("Set product width: ");
            double width = io.readDouble();

            io.println("Select plank type:");
            int j = 0;
            for (TipoTablero c : TipoTablero.values()) {
                io.println(j + ": " + c.toString());
                j++;
            }
            io.print("Select an option: ");
            int option = io.readInt();
            TipoTablero tipoTablero = TipoTablero.values()[option];

            return new Tablero(id, description, "provider", stock, price, height, tipoTablero, width, "Tablero");
        };

        Varnish<Barniz> createVarnish = () -> {
            io.println("Set product id: ");
            int id = io.readInt();
            io.readln();
            io.println("Set product description: ");
            String description = io.readln();
            io.println("Set product stock: ");
            int stock = io.readInt();
            io.readln();
            io.println("Set product price: ");
            double price = io.readDouble();
            io.readln();
            io.println("Set product volume: ");
            int mililitros = io.readInt();

            return new Barniz(id, description, "provider", stock, price, ColorBarniz.CAOBA, mililitros, "Barniz");
        };

        Runnable listAllProducts = () -> {
            for (Producto producto : db.getProductos()) {
                io.println(" " + producto.getType() + " " + producto.getId() + " ");
            }
        };

        Runnable searchProductByID = () -> {
            io.print("Enter the ID: ");
            int id = io.readInt();
            for (Producto producto : db.getProductos()) {
                if (producto.getId() == id) {
                    io.println(" " + producto.getType() + " " + producto.getId() + " ");
                }
            }
        };

        Runnable storeOpt = () -> {
            LinkedHashMap<Integer, ExtendedMenu.OpcionMenu> subOpciones = new LinkedHashMap<>();
            io.println("Insert the id: ");
            int id = io.readInt();
            io.readln();
            for (Store store : stores) {
                if (store.getId() == id) {
                    io.println("" + store.getId());
                    subOpciones.put(1, new ExtendedMenu.OpcionMenu("Get name", () -> io.println(store.getNombre())));
                    subOpciones.put(2, new ExtendedMenu.OpcionMenu(
                            "Add product to the store", () -> {
                        int x = 0;
                        for (Producto producto : db.getProductos()) {
                            io.println(x + ": ID: " + producto.getId() + " Name: " + producto.getDescription());
                            x++;
                        }
                        db.addProductToStoreEntry(12, 112323);
                    }));
                    ExtendedMenu.mostrarMenu("Store Manager", subOpciones);
                }
            }
        };

        Runnable storesMgr = () -> {
            LinkedHashMap<Integer, ExtendedMenu.OpcionMenu> subOpciones = new LinkedHashMap<>();
            subOpciones.put(1, new ExtendedMenu.OpcionMenu("Add store", addStore));
            subOpciones.put(2, new ExtendedMenu.OpcionMenu("List Stores", getStores));
            subOpciones.put(3, new ExtendedMenu.OpcionMenu("Enter store options", storeOpt));
            io.println("");
            ExtendedMenu.mostrarMenu("Stores management", subOpciones);
        };

        // Main menu loop
        while (!salir) {
            LinkedHashMap<Integer, ExtendedMenu.OpcionMenu> subOpciones = new LinkedHashMap<>();
            subOpciones.put(1, new ExtendedMenu.OpcionMenu("Stores manager", storesMgr));
            subOpciones.put(2, new ExtendedMenu.OpcionMenu("Provider Manager", () -> {})); // placeholder
            subOpciones.put(3, new ExtendedMenu.OpcionMenu("Products manager", () -> {})); // placeholder
            ExtendedMenu.mostrarMenu("Menú Principal", subOpciones);
        }
    }
}
