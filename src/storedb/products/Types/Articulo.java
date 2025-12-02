package storedb.products.Types;

import storedb.products.Producto;
import storedb.products.Types.Subclasses.TipoArticulo;

public class Articulo extends Producto {

    private TipoArticulo tipoArticulo;

    public Articulo(String description, int id, double price, String provider, int stock, String type, TipoArticulo tipoArticulo) {
        super(description, id, price, provider, stock, type);
        this.tipoArticulo = tipoArticulo;
    }

    public TipoArticulo getTipoArticulo() {
        return tipoArticulo;
    }

    public void setTipoArticulo(TipoArticulo tipoArticulo) {
        this.tipoArticulo = tipoArticulo;
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public Double getPrice() {
        return super.getPrice();
    }

    @Override
    public String getProvider() {
        return super.getProvider();
    }

    @Override
    public int getStock() {
        return super.getStock();
    }

    @Override
    public String getType() {
        return super.getType();
    }

    public Articulo() {
        super();
    }

    public Articulo(String description, int id, double price, String provider, int stock, String type) {
        super(description, id, price, provider, stock, type);
    }

    @Override
    public void setDescription(String description) {
        super.setDescription(description);
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public void setPrice(double price) {
        super.setPrice(price);
    }

    @Override
    public void setPrice(Double price) {
        super.setPrice(price);
    }

    @Override
    public void setProvider(String provider) {
        super.setProvider(provider);
    }

    @Override
    public void setStock(int stock) {
        super.setStock(stock);
    }

    @Override
    public void setType(String type) {
        super.setType(type);
    }

    @Override
    public String toString() {
        return String.format("%s(id:%d, type:%s, stock:%d, provider:%s)",
                getDescription(), getId(), tipoArticulo, getStock(), getProvider());
    }

}
