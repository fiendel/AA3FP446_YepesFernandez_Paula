package storedb.products.Types;
import storedb.products.Producto;
import storedb.products.Types.Subclasses.ColorBarniz;
import storedb.providers.Provider;

public class Barniz extends Producto {

    private int mililitros;
    private ColorBarniz colorBarniz;

    public Barniz(int id, String description, Provider provider, int stock, double price, ColorBarniz colorBarniz, int mililitros) {
        super(id, description, provider, stock, price);
        this.colorBarniz = colorBarniz;
        this.mililitros = mililitros;
    }




    public ColorBarniz getColorBarniz() {
        return colorBarniz;
    }

    public void setColorBarniz(ColorBarniz colorBarniz) {
        this.colorBarniz = colorBarniz;
    }

    public int getMililitros() {
        return mililitros;
    }

    public void setMililitros(int mililitros) {
        this.mililitros = mililitros;
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
    public Provider getProvider() {
        return super.getProvider();
    }

    @Override
    public int getStock() {
        return super.getStock();
    }

    public Barniz() {
        super();
    }

    public Barniz(int id, String description, Provider provider, int stock, double price) {
        super(id, description, provider, stock, price);
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
    public void setProvider(Provider provider) {
        super.setProvider(provider);
    }

    @Override
    public void setStock(int stock) {
        super.setStock(stock);
    }
}
