package storedb.products;

import storedb.providers.Provider;

public abstract class  Producto {

    private int id;
    private String description;
    private String provider;
    private double price;
    private int stock;
    private String type = "Generic";

public Producto(String description, int id, double price, String provider, int stock, String type) {
        this.description = description;
        this.id = id;
        this.price = price;
        this.provider = provider;
        this.stock = stock;
        this.type = type;
    }

    public Producto(){

    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


}
