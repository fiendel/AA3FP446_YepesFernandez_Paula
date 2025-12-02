package storedb;

import storedb.products.Producto;
import storedb.providers.Provider;
import storedb.stores.Store;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.io.Serializable;

public class DB implements Serializable {
    List<Store> stores = new LinkedList<>();
    List<Producto> productos = new LinkedList<>();
    List<Provider> providers = new LinkedList<>();
    private static final long serialVersionUID = 1L;

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


    }


    public void deleteProvider(int providerIndex) {

        // Old and new provider information
        Provider provider = providers.get(providerIndex);
        String oldNif = provider.getNif();
        providers.remove(providerIndex);
        // DELETE all entries in productToProvider where storedNif == oldNif
        productToProvider.entrySet().removeIf(entry ->
                entry.getValue().equals(oldNif)
        );
    }


    public void modifyProduct(int productIndex, Producto producto){
        // Old and new values
        int oldID = productos.get(productIndex).getId();
        int newID = producto.getId();
        // Update product in the list
        productos.get(productIndex).setId(newID);
        productos.get(productIndex).setDescription(producto.getDescription());
        productos.get(productIndex).setStock(producto.getStock());
        productos.get(productIndex).setPrice(producto.getPrice());
        productos.get(productIndex).setStock(producto.getStock());
        productos.get(productIndex).setProvider(producto.getProvider());


        // FIX: update all product → provider mappings
        productToProvider.replaceAll((productId, storedNif) ->
                String.valueOf(productId.equals(oldID) ? newID : productId)
        );
    }
    public void deleteProduct(int productIndex) {

        // Old and new provider information
        Producto producto =  productos.get(productIndex);
        int oldID = producto.getId();
        productos.remove(productIndex);
        // DELETE all entries in productToProvider where storedNif == oldNif
        productToProvider.entrySet().removeIf(entry ->
                entry.getKey().equals(oldID)
        );
    }

    public LinkedList<Producto> getProductsByStore(int storeId) {
        LinkedList<Producto> result = new LinkedList<>();

        // Loop through all product → store mappings
        for (var entry : productToStore.entrySet()) {
            int productId = entry.getKey();
            int mappedStoreId = entry.getValue();

            if (mappedStoreId == storeId) {
                // Find the product in productos list
                for (Producto p : productos) {
                    if (p.getId() == productId) {
                        result.add(p);
                        break;
                    }
                }
            }
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Database Snapshot\n\n");

        sb.append("Stores:\n");
        for (Store store : stores) {
            sb.append(String.format("  - ID: %d, Name: %s\n", store.getId(), store.getNombre()));
        }
        if (stores.isEmpty()) sb.append("  (No stores)\n");

        sb.append("\nProducts:\n");
        for (Producto producto : productos) {
            sb.append("  " + producto + "\n"); // Uses Producto.toString()
        }
        if (productos.isEmpty()) sb.append("  (No products)\n");

        sb.append("\nProviders:\n");
        for (Provider provider : providers) {
            sb.append(String.format("  - NIF: %s, Name: %s\n", provider.getNif(), provider.getNombre()));
        }
        if (providers.isEmpty()) sb.append("  (No providers)\n");

        sb.append("\nProduct → Store Mappings:\n");
        for (var entry : productToStore.entrySet()) {
            sb.append(String.format("  Product ID %d → Store ID %d\n", entry.getKey(), entry.getValue()));
        }
        if (productToStore.isEmpty()) sb.append("  (No product-store mappings)\n");

        sb.append("\nProduct → Provider Mappings:\n");
        for (var entry : productToProvider.entrySet()) {
            sb.append(String.format("  Product ID %d → Provider NIF %s\n", entry.getKey(), entry.getValue()));
        }
        if (productToProvider.isEmpty()) sb.append("  (No product-provider mappings)\n");

        sb.append("\nEnd of DB Snapshot\n");
        return sb.toString();
    }
}
