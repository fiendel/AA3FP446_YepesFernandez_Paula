package storedb;

import storedb.products.Producto;
import storedb.providers.Provider;
import storedb.stores.Store;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class DB implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private List<Store> stores = new LinkedList<>();
    private List<Producto> productos = new LinkedList<>();
    private List<Provider> providers = new LinkedList<>();

    private LinkedHashMap<Integer, Integer> productToStore = new LinkedHashMap<>();
    private LinkedHashMap<Integer, String> productToProvider = new LinkedHashMap<>();

    public DB() {}

    public void addProductToProviderEntry(int productID, String providerNIF) {
        productToProvider.put(productID, providerNIF);
    }

    public void addProductToStoreEntry(int productID, int storeID) {
        productToStore.put(productID, storeID);
    }

    public void addProvider(Provider provider) {
        providers.add(provider);
    }

    public void addProcduct(Producto producto) {
        productos.add(producto);
    }

    public void addStore(Store e) {
        stores.add(e);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public List<Provider> getProviders() {
        return providers;
    }

    public List<Store> getStores() {
        return stores;
    }

    public LinkedHashMap<Integer, Integer> getProductToStore() {
        return productToStore;
    }

    public LinkedHashMap<Integer, String> getProductToProvider() {
        return productToProvider;
    }

    public void modifyProvider(int providerIndex, Provider provider) {
        String oldNif = providers.get(providerIndex).getNif();
        String newNif = provider.getNif();

        providers.get(providerIndex).setNif(newNif);
        providers.get(providerIndex).setNombre(provider.getNombre());

        // Actualizar mappings de productos
        productToProvider.replaceAll((productId, storedNif) ->
                storedNif.equals(oldNif) ? newNif : storedNif
        );
    }

    public void deleteProvider(int providerIndex) {
        Provider provider = providers.get(providerIndex);
        String oldNif = provider.getNif();
        providers.remove(providerIndex);

        productToProvider.entrySet().removeIf(entry -> entry.getValue().equals(oldNif));
    }

    public void modifyProduct(int productIndex, Producto producto) {
        int oldID = productos.get(productIndex).getId();
        int newID = producto.getId();

        productos.get(productIndex).setId(newID);
        productos.get(productIndex).setDescription(producto.getDescription());
        productos.get(productIndex).setStock(producto.getStock());
        productos.get(productIndex).setPrice(producto.getPrice());
        productos.get(productIndex).setProvider(producto.getProvider());

        // Actualizar mappings de producto → provider
        if (oldID != newID) {
            if (productToProvider.containsKey(oldID)) {
                String providerNIF = productToProvider.remove(oldID);
                productToProvider.put(newID, providerNIF);
            }
        }

        if (productToStore.containsKey(oldID)) {
            int storeID = productToStore.remove(oldID);
            productToStore.put(newID, storeID);
        }
    }

    public void deleteProduct(int productIndex) {
        Producto producto = productos.get(productIndex);
        int oldID = producto.getId();
        productos.remove(productIndex);

        productToProvider.remove(oldID);
        productToStore.remove(oldID);
    }

    public LinkedList<Producto> getProductsByStore(int storeId) {
        LinkedList<Producto> result = new LinkedList<>();

        for (var entry : productToStore.entrySet()) {
            if (entry.getValue() == storeId) {
                for (Producto p : productos) {
                    if (p.getId() == entry.getKey()) {
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
        if (stores.isEmpty()) sb.append("  (No stores)\n");
        else stores.forEach(store -> sb.append(String.format("  - ID: %d, Name: %s\n", store.getId(), store.getNombre())));

        sb.append("\nProducts:\n");
        if (productos.isEmpty()) sb.append("  (No products)\n");
        else productos.forEach(producto -> sb.append("  " + producto + "\n"));

        sb.append("\nProviders:\n");
        if (providers.isEmpty()) sb.append("  (No providers)\n");
        else providers.forEach(provider -> sb.append(String.format("  - NIF: %s, Name: %s\n", provider.getNif(), provider.getNombre())));

        sb.append("\nProduct → Store Mappings:\n");
        if (productToStore.isEmpty()) sb.append("  (No product-store mappings)\n");
        else productToStore.forEach((prodId, storeId) -> sb.append(String.format("  Product ID %d → Store ID %d\n", prodId, storeId)));

        sb.append("\nProduct → Provider Mappings:\n");
        if (productToProvider.isEmpty()) sb.append("  (No product-provider mappings)\n");
        else productToProvider.forEach((prodId, nif) -> sb.append(String.format("  Product ID %d → Provider NIF %s\n", prodId, nif)));

        sb.append("\nEnd of DB Snapshot\n");
        return sb.toString();
    }
}
