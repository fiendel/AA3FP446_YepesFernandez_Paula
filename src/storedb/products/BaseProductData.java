package storedb.products;

import com.qol.IO;
import storedb.DB;
import storedb.providers.Provider;

public record BaseProductData(
        int id,
        String description,
        String providerNIF,
        int stock,
        double price
) {
    public static BaseProductData askBaseProductData(IO io, DB db) {
        io.println("Enter product ID: ");
        int id = io.readInt();

        io.println("Enter product description: ");
        String description = io.readln();

        io.println("Select the product provider:");
        int i = 0;
        for (Provider provider : db.getProviders()) {
            io.println(i + ": " + provider);
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
}
