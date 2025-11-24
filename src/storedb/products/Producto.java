package storedb.products;

import storedb.providers.Provider;

public class  Producto {

    private int id;
    private String description;
    private Provider provider;
    private double price;
    private int stock;

    public Producto(int id, String description, Provider provider, int stock,  double price){
        setId(id);
        setDescription(description);
        setProvider(provider);
        setPrice(price);
        setStock(stock);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


}
