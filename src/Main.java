import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.qol.storeDB.IODB;
import storedb.products.*;
import com.utils.*;
import com.qol.*;
import storedb.products.Types.Articulo;
import storedb.products.Types.Barniz;
import storedb.products.Types.Subclasses.ColorBarniz;
import storedb.products.Types.Subclasses.TipoArticulo;
import storedb.products.Types.Subclasses.TipoTablero;
import storedb.products.Types.Tablero;
import storedb.providers.Provider;
import storedb.stores.Store;
import storedb.DB;

public class Main {

    //region INPUT HELPERS
    // ============================================================
    // 0. SHARED PRODUCT INPUT HELPERS
    // ============================================================

    private record BaseProductData(int id, String description, String providerNIF, int stock, double price) {
    }

    private static BaseProductData askBaseProductData(IO io, DB db) {
        io.println("Enter product ID: ");
        int id = io.readInt();

        io.println("Enter product description: ");
        String description = io.readln();

        // Provider selection
        io.println("Select the product provider:");
        int i = 0;
        for (Provider provider : db.getProviders()) {
            io.println(i + ": " + provider);  // Use toString
            i++;
        }
        io.print("Option: ");
        int providerIndex = io.readInt();
        String providerNIF = db.getProviders().get(providerIndex).getNif();
        db.addProductToProviderEntry(id, providerNIF);

        io.println("Enter product stock quantity: ");
        int stock = io.readInt();
        io.readln();

        io.println("Enter product price: ");
        double price = io.readDouble();
        io.readln();

        return new BaseProductData(id, description, providerNIF, stock, price);
    }
    //endregion

    // ============================================================
    public static void main(String[] args) {

        final IODB io = new IODB();
        List<Store> stores = new LinkedList<>();
        DB db = new DB();
        final DB[] dbRef = new DB[]{db}; // wrap DB so lambdas can modify it

        //region RAW INTERFACES
        interface Table<Tablero> {
            Tablero get();
        }
        interface Varnish<Barniz> {
            Barniz get();
        }
        interface Generic<Articulo> {
            Articulo get();
        }
        //endregion

        //region STORE MANAGEMENT
        Runnable addStore = () -> {
            io.print("Enter store ID: ");
            int id = io.readInt();
            io.print("Enter store name: ");
            String name = io.readln();
            stores.add(new Store(id, name));
            io.println("Store added: " + id + " | " + name);
        };

        Runnable listStores = () -> {
            if (stores.isEmpty()) {
                io.println("No stores available.");
            } else {
                io.println("Stores:");
                for (Store store : stores) {
                    io.println(store.toString());
                }
            }
        };

        Runnable storeOptions = () -> {
            io.println("Enter the store ID: ");
            int id = io.readInt();
            for (Store store : stores) {
                if (store.getId() == id) {
                    LinkedHashMap<Integer, ExtendedMenu.OpcionMenu> options = new LinkedHashMap<>();
                    options.put(1, new ExtendedMenu.OpcionMenu("Show store info",
                            () -> io.println(store.toString())));
                    options.put(2, new ExtendedMenu.OpcionMenu("Add product to this store", () -> {
                        int index = 0;
                        for (Producto producto : dbRef[0].getProductos()) {
                            io.println(index + ": " + producto);
                            index++;
                        }
                        io.print("Select product index: ");
                        int productIndex = io.readInt();
                        dbRef[0].addProductToStoreEntry(dbRef[0].getProductos().get(productIndex).getId(), id);
                        io.println("Product added to store.");
                    }));
                    ExtendedMenu.mostrarMenu("Store Management", options);
                }
            }
        };

        Runnable storeManager = () -> {
            LinkedHashMap<Integer, ExtendedMenu.OpcionMenu> options = new LinkedHashMap<>();
            options.put(1, new ExtendedMenu.OpcionMenu("Add a store", addStore));
            options.put(2, new ExtendedMenu.OpcionMenu("List all stores", listStores));
            options.put(3, new ExtendedMenu.OpcionMenu("Manage a store", storeOptions));
            ExtendedMenu.mostrarMenu("Store Management Menu", options);
        };
        //endregion

        //region PRODUCT CREATION
        Table<Tablero> createTablero = () -> {
            var data = Main.askBaseProductData(io, dbRef[0]);
            io.println("Enter product height: ");
            double height = io.readDouble();
            io.readln();
            io.println("Enter product width: ");
            double width = io.readDouble();
            io.println("Select plank type:");
            int j = 0;
            for (TipoTablero type : TipoTablero.values()) {
                io.println(j + ": " + type);
                j++;
            }
            io.print("Option: ");
            TipoTablero type = TipoTablero.values()[io.readInt()];

            return new Tablero(data.id(), data.description(), data.providerNIF(),
                    data.stock(), data.price(), height, type, width, "Tablero");
        };

        Varnish<Barniz> createVarnish = () -> {
            var data = Main.askBaseProductData(io, dbRef[0]);
            io.println("Enter product volume (ml): ");
            int ml = io.readInt();
            io.println("Select varnish color:");
            int j = 0;
            for (ColorBarniz color : ColorBarniz.values()) {
                io.println(j + ": " + color);
                j++;
            }
            io.print("Option: ");
            ColorBarniz selectedColor = ColorBarniz.values()[io.readInt()];

            return new Barniz(data.id(), data.description(), data.providerNIF(),
                    data.stock(), data.price(), selectedColor, ml, "Barniz");
        };

        Generic<Articulo> createGeneric = () -> {
            var data = Main.askBaseProductData(io, dbRef[0]);
            io.println("Select article type:");
            int j = 0;
            for (TipoArticulo type : TipoArticulo.values()) {
                io.println(j + ": " + type);
                j++;
            }
            io.print("Option: ");
            TipoArticulo selectedType = TipoArticulo.values()[io.readInt()];

            return new Articulo(data.description(), data.id(), data.price(),
                    data.providerNIF(), data.stock(), "Generic", selectedType);
        };
        //endregion

        //region PRODUCT MANAGEMENT
        Runnable listAllProducts = () -> {
            if (dbRef[0].getProductos().isEmpty()) {
                io.println("No products available.");
            } else {
                io.println("Products:");
                for (Producto p : dbRef[0].getProductos()) io.println(p.toString());
            }
        };

        Runnable searchByProvider = () -> {
            if (dbRef[0].getProviders().isEmpty()) {
                io.println("No providers available.");
                return;
            }
            io.println("Select provider:");
            int i = 0;
            for (Provider p : dbRef[0].getProviders()) {
                io.println(i + ": " + p);
                i++;
            }
            io.print("Option: ");
            String providerNIF = dbRef[0].getProviders().get(io.readInt()).getNif();
            io.println("Products from selected provider:");
            for (Producto p : dbRef[0].getProductos()) {
                if (p.getProvider().equals(providerNIF)) io.println(p.toString());
            }
        };

        Runnable searchByStore = () -> {
            if (dbRef[0].getStores().isEmpty()) {
                io.println("No stores available.");
                return;
            }
            io.println("Select store:");
            int i = 0;
            for (Store store : dbRef[0].getStores()) {
                io.println(i + ": " + store);
                i++;
            }
            io.print("Option: ");
            LinkedList<Producto> productsByStore = dbRef[0].getProductsByStore(
                    dbRef[0].getStores().get(io.readInt()).getId());
            io.println("Products in selected store:");
            for (Producto p : productsByStore) io.println(p.toString());
        };

        Runnable modifyProduct = () -> {
            io.println("Select product to modify:");
            int i = 0;
            for (Producto p : dbRef[0].getProductos()) io.println(i++ + ": " + p);
            io.print("Option: ");
            int selection = io.readInt();
            Producto producto = dbRef[0].getProductos().get(selection);
            switch (producto.getType()) {
                case "Tablero" -> dbRef[0].modifyProduct(selection, createTablero.get());
                case "Barniz" -> dbRef[0].modifyProduct(selection, createVarnish.get());
                case "Generic" -> dbRef[0].modifyProduct(selection, createGeneric.get());
            }
            io.println("Product modified successfully.");
        };

        Runnable createProduct = () -> {
            LinkedHashMap<Integer, ExtendedMenu.OpcionMenu> options = new LinkedHashMap<>();
            io.println("--CAUTION-- Product type cannot be changed later.");
            options.put(1, new ExtendedMenu.OpcionMenu("Create plank", () -> dbRef[0].addProcduct(createTablero.get())));
            options.put(2, new ExtendedMenu.OpcionMenu("Create varnish", () -> dbRef[0].addProcduct(createVarnish.get())));
            options.put(3, new ExtendedMenu.OpcionMenu("Create generic", () -> dbRef[0].addProcduct(createGeneric.get())));
            ExtendedMenu.mostrarMenu("Product Creation Menu", options);
        };

        Runnable deleteProduct = () -> {
            io.println("Select product to delete:");
            int i = 0;
            for (Producto p : dbRef[0].getProductos()) io.println(i++ + ": " + p);
            io.print("Option: ");
            int selection = io.readInt();
            dbRef[0].deleteProduct(selection);
            io.println("Product deleted successfully.");
        };

        Runnable productManager = () -> {
            LinkedHashMap<Integer, ExtendedMenu.OpcionMenu> options = new LinkedHashMap<>();
            options.put(1, new ExtendedMenu.OpcionMenu("Create product", createProduct));
            options.put(2, new ExtendedMenu.OpcionMenu("List all products", listAllProducts));
            options.put(3, new ExtendedMenu.OpcionMenu("Search products by provider", searchByProvider));
            options.put(4, new ExtendedMenu.OpcionMenu("Search products by store", searchByStore));
            options.put(5, new ExtendedMenu.OpcionMenu("Modify a product", modifyProduct));
            options.put(6, new ExtendedMenu.OpcionMenu("Delete a product", deleteProduct));
            ExtendedMenu.mostrarMenu("Product Management Menu", options);
        };
        //endregion

        //region PROVIDER MANAGEMENT
        Runnable createProvider = () -> {
            io.println("Enter provider NIF: ");
            String nif = io.readln();
            io.println("Enter provider name: ");
            String name = io.readln();
            dbRef[0].addProvider(new Provider(nif, name));
            io.println("Provider created successfully.");
        };

        Runnable modifyProvider = () -> {
            io.println("Select provider to modify:");
            int i = 0;
            for (Provider p : dbRef[0].getProviders()) io.println(i++ + ": " + p);
            io.print("Option: ");
            int selection = io.readInt();
            io.println("Enter new provider NIF: ");
            String nif = io.readln();
            io.println("Enter new provider name: ");
            String name = io.readln();
            dbRef[0].modifyProvider(selection, new Provider(nif, name));
            io.println("Provider modified successfully.");
        };

        Runnable deleteProvider = () -> {
            io.println("Select provider to delete:");
            int i = 0;
            for (Provider p : dbRef[0].getProviders()) io.println(i++ + ": " + p);
            io.print("Option: ");
            int selection = io.readInt();
            dbRef[0].deleteProvider(selection);
            io.println("Provider deleted successfully.");
        };

        Runnable providerManager = () -> {
            LinkedHashMap<Integer, ExtendedMenu.OpcionMenu> options = new LinkedHashMap<>();
            options.put(1, new ExtendedMenu.OpcionMenu("Create provider", createProvider));
            options.put(2, new ExtendedMenu.OpcionMenu("List all providers", () -> {
                io.println("Providers:");
                for (Provider p : dbRef[0].getProviders()) io.println(p.toString());
            }));
            options.put(3, new ExtendedMenu.OpcionMenu("Modify provider", modifyProvider));
            options.put(4, new ExtendedMenu.OpcionMenu("Delete provider", deleteProvider));
            ExtendedMenu.mostrarMenu("Provider Management Menu", options);
        };
        //endregion

        //region MAIN MENU
        AtomicBoolean exitApp = new AtomicBoolean(false);
        while (!exitApp.get()) {
            LinkedHashMap<Integer, ExtendedMenu.OpcionMenu> mainOptions = new LinkedHashMap<>();
            mainOptions.put(1, new ExtendedMenu.OpcionMenu("Store management", storeManager));
            mainOptions.put(2, new ExtendedMenu.OpcionMenu("Provider management", providerManager));
            mainOptions.put(3, new ExtendedMenu.OpcionMenu("Product management", productManager));
            mainOptions.put(4, new ExtendedMenu.OpcionMenu("Save database to file", () -> {
                io.print("Enter filename: ");
                String filename = io.readln();
                io.saveDBToFile(dbRef[0], filename);
            }));
            mainOptions.put(5, new ExtendedMenu.OpcionMenu("Load database from file", () -> {
                io.print("Enter filename: ");
                dbRef[0] = io.loadDBFromFile(io.readln());
            }));
            mainOptions.put(6, new ExtendedMenu.OpcionMenu("Exit application", () -> {
                System.out.println("Exiting application. Goodbye!");
                exitApp.set(true);
            }));
            ExtendedMenu.mostrarMenu("Main Menu", mainOptions, false);
        }
        //endregion
    }
}

