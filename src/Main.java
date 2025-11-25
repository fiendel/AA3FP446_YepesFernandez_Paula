import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import storedb.products.*;
import com.utils.*;
import com.qol.*;
import storedb.products.Types.Subclasses.TipoTablero;
import storedb.products.Types.Tablero;
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

        interface Supplier<Provider> {
            Provider get(); // Devuelve algo del tipo T
        }

        interface Table<Tablero> {
            Tablero get(); // Devuelve algo del tipo T
        }

        interface Product<Producto> {
            Producto get(); // Devuelve algo del tipo T
        }

        interface Varnish<Barniz>{

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

        Table createTablero = () ->{
            io.println("Set product id: ");
            int id = io.readInt();
            io.readln();
            io.println("Set product description: ");
            String description = io.readln();
            io.println("Set product provider: ");
            io.println("Set provider NIF : ");
            String providerId = io.readln();
            io.readln();
            io.println("Set provider name : ");
            String providerName = io.readln();
            Provider provider = new Provider(providerId,providerName);
            io.println("Set product stock: ");
            int stock = io.readInt();
            io.readln();
            io.println("Set product price: ");
            double price = io.readDouble();
            io.readln();
            io.println("Set product height: ");
            double height = io.readDouble();
            io.readln();
            io.println("Set product height: ");
            double width = io.readDouble();
            io.readln();


            return new Tablero(
                    id,                     // id
                    description,    // description
                    provider,                   // provider
                    stock,                         // stock
                    price,                      // price
                    height,                        // height (metros)
                    TipoTablero.MDF,            // tipoTablero
                    width                      // width (metros)
            );
        };

        Table createTablero = () ->{
            io.println("Set product id: ");
            int id = io.readInt();
            io.readln();
            io.println("Set product description: ");
            String description = io.readln();
            io.println("Set product provider: ");
            io.println("Set provider NIF : ");
            String providerId = io.readln();
            io.readln();
            io.println("Set provider name : ");
            String providerName = io.readln();
            Provider provider = new Provider(providerId,providerName);
            io.println("Set product stock: ");
            int stock = io.readInt();
            io.readln();
            io.println("Set product price: ");
            double price = io.readDouble();
            io.readln();
            io.println("Set product height: ");
            double height = io.readDouble();
            io.readln();
            io.println("Set product height: ");
            double width = io.readDouble();
            io.readln();


            return new Tablero(
                    id,                     // id
                    description,    // description
                    provider,                   // provider
                    stock,                         // stock
                    price,                      // price
                    height,                        // height (metros)
                    TipoTablero.MDF,            // tipoTablero
                    width                      // width (metros)
            );
        };


        Product addProduct = () ->{
            final Producto[] producto = new Producto[1];
            LinkedHashMap<Integer, ExtendedMenu.OpcionMenu> subOpciones = new LinkedHashMap<>();
            subOpciones.put(1, new ExtendedMenu.OpcionMenu("Add plank", () ->{
                     producto[0] = (Producto)createTablero.get();
            }));

            subOpciones.put(2, new ExtendedMenu.OpcionMenu("Add varnish", () ->{
                producto[0] = (Producto)createTablero.get();
            }));

            ExtendedMenu.mostrarMenu("Add product", subOpciones);
            return producto[0];


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
                    subOpciones.put(2, new ExtendedMenu.OpcionMenu("Add product",
                                                                    () -> {store.addProduct((Producto)addProduct.get());
                                                                            store.getProduct();
                                                                    }));

                }

            }
            ExtendedMenu.mostrarMenu("Store Manager", subOpciones);

        };

        Runnable storesMgr = () ->{
            LinkedHashMap<Integer, ExtendedMenu.OpcionMenu> subOpciones = new LinkedHashMap<>();
            subOpciones.put(1, new ExtendedMenu.OpcionMenu("Add store", addStore));
            subOpciones.put(2, new ExtendedMenu.OpcionMenu("List Stores", getStores));
            subOpciones.put(3, new ExtendedMenu.OpcionMenu("Enter store manager", mngStore));
            io.println("");
            io.println("Provider options: ");
            //subOpciones.put(3, new ExtendedMenu.OpcionMenu("Add provider", () -> addProvider.get()));
            subOpciones.put(4, new ExtendedMenu.OpcionMenu("Get stock", getStock));
            ExtendedMenu.mostrarMenu("Stores management", subOpciones);
        };

        Runnable menuEnTest = () -> {

            LinkedHashMap<Integer, MenuAvanzado.OpcionMenu> subOpciones = new LinkedHashMap<>();
            //subOpciones.put(1, new MenuAvanzado.OpcionMenu("Listar  producto", () -> io.println("Jaarl")));
            subOpciones.put(1, new MenuAvanzado.OpcionMenu("Stores manager", storesMgr));

            MenuAvanzado.mostrarMenu("Menú Principal", subOpciones);

        };

        while (!salir) {
            LinkedHashMap<Integer, MenuAvanzado.OpcionMenu> menuPrincipal = new LinkedHashMap<>();
            menuPrincipal.put(1, new MenuAvanzado.OpcionMenu("Gestión en test", menuEnTest));

            //menuPrincipal.put(2, new MenuAvanzado.OpcionMenu("Consultar stock", menuEnTest));
            //menuPrincipal.put(3, new MenuAvanzado.OpcionMenu("Salir", () -> salir = true));

            MenuAvanzado.mostrarMenu("Menú Principal", menuPrincipal);
        }
    }
}
