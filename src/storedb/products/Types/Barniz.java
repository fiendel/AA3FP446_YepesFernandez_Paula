package storedb.products.Types;
import storedb.products.Producto;
import storedb.products.Types.Subclasses.ColorBarniz;
import storedb.providers.Provider;

public class Barniz extends Producto {

    private int mililitros;
    private ColorBarniz colorBarniz;
    private String type;

    public Barniz(int id, String description, String provider, int stock, double price, ColorBarniz colorBarniz, int mililitros, String type) {
        super(description, id, price, provider, stock, type);
        this.type = type;
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
    public String  getProvider() {
        return super.getProvider();
    }

    @Override
    public int getStock() {
        return super.getStock();
    }

    public Barniz() {
        super();
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
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setStock(int stock) {
        super.setStock(stock);
    }

    @Override
    public String toString() {
        return String.format("%s(id:%d, color:%s, volume:%dml, stock:%d, price:%.2f, provider:%s)",
                getDescription(),
                getId(),
                colorBarniz,
                mililitros,
                getStock(),
                getPrice(),
                getProvider());
    }

}
