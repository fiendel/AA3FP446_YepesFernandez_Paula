package storedb;

import storedb.products.Producto;
import storedb.providers.Provider;
import storedb.stores.Store;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class DB {
    List<Store> stores = new LinkedList<>();
    List<Producto> productos = new LinkedList<>();
    List<Provider> providers = new LinkedList<>();

    LinkedHashMap<Integer, Integer> productToStore = new LinkedHashMap<>();
    LinkedHashMap<Integer,String> productToProvider = new LinkedHashMap<>();



    public DB() {

    }



    public void addProductToProviderEntry(int productID, String providerNIF){
        productToProvider.put( productID,providerNIF);

    }

    public void addProductToStoreEntry(int productID, int storID){
        productToStore.put(productID, storID);

    }

    public void addProvider(Provider provider){
        providers.add(provider);

    }

    public void addProcduct(Producto producto){
        productos.add(producto);

    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<Provider> providers) {
        this.providers = providers;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    public LinkedHashMap<Integer, Integer> getProductToStore() {
        return productToStore;
    }

    public void setProductToStore(LinkedHashMap<Integer, Integer> productToStore) {
        this.productToStore = productToStore;
    }

    public void modifyProvider(int providerIndex, Provider provider) {

        // Old and new values
        String oldNif = providers.get(providerIndex).getNif();
        String newNif = provider.getNif();

        // Update provider in the list
        providers.get(providerIndex).setNif(newNif);
        providers.get(providerIndex).setNombre(provider.getNombre());


        // FIX: update all product → provider mappings
        productToProvider.replaceAll((productId, storedNif) ->
                storedNif.equals(oldNif) ? newNif : storedNif
        );

        providers.get(providerIndex).setNombre(provider.getNombre());
        providers.get(providerIndex).setNif(provider.getNif());
    }


    public void deleteProvider(int providerIndex) {

        // Old and new provider information
        String oldNif = providers.get(providerIndex).getNif();

        // DELETE all entries in productToProvider where storedNif == oldNif
        productToProvider.entrySet().removeIf(entry ->
                entry.getValue().equals(oldNif)
        );
    }

    
    // Method to display a table of products, stores, and providers
    public void displayProductTable() {
        System.out.printf("%-5s %-20s %-20s %-20s%n", "ID", "Product", "Store", "Provider");
        System.out.println("---------------------------------------------------------------------");

        for (Producto producto : productos) {
            int productId = producto.getId();
            String productName = producto.getDescription();

            // Get store name if exists
            String storeName = "N/A";
            if (productToStore.containsKey(productId)) {
                int storeId = productToStore.get(productId);
                for (Store store : stores) {
                    if (store.getId() == storeId) {
                        storeName = store.getNombre();
                        break;
                    }
                }
            }

            // Get provider name if exists
            String providerName = "N/A";
            if (productToProvider.containsKey(productId)) {
                String providerNIF = productToProvider.get(productId);
                for (Provider provider : providers) {
                    if (provider.getNif().equals(providerNIF)) {
                        providerName = provider.getNombre();
                        break;
                    }
                }
            }

            System.out.printf("%-5d %-20s %-20s %-20s%n", productId, productName, storeName, providerName);
        }
    }


}
