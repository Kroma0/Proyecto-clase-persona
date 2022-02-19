
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Product implements Identificable, Serializable, Comparable<Product>{

    private Integer idProduct;
    private String name;
    private int price;
    private int stock;
    private LocalDate startCatalogue;
    private LocalDate endingCatalogue;
    private static final long serialVersionUID = 6529685098267757690L;
    
    @Override
    public int getId() {
        return this.idProduct;
    }   
    //Getters dels productes
    public Integer getIdProduct() {
        return idProduct;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    //Setters del producte
    public void setIdproduct(int i) {
        this.idProduct = i;
    }

    public void setName(String n) {
        this.name = n;
    }

    public void setPrice(int p) {
        this.price = p;
    }

    public void setStock(int s) {
        this.stock = s;
    }

    //El constructors del produte
    public Product(int idproduct, String nom, int price, int stock, LocalDate startCatalogue, LocalDate endingCatalogue) {
        this.idProduct = idproduct;
        this.name = nom;
        this.price = price;
        this.stock = stock;
        this.startCatalogue = startCatalogue;
        this.endingCatalogue = endingCatalogue;
    }

    public Product(int idproduct, String nom, int price) {
        this.idProduct = idproduct;
        this.name = nom;
        this.price = price;

    }
    //El metode toString
    @Override
    public String toString() {
        return "Producte{" + "idproduct=" + idProduct + ", nom=" + name + ", price=" + price + ", stock=" + stock + ", startCatalogue=" + startCatalogue + ", endingCatalogue=" + endingCatalogue +'}';
    }

    //Metode equals del name
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Product) {
            Product temp = (Product) obj;
            return temp.getName().equals(obj);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.name);
        return hash;
    }
    
    public void putStock(int amount){
        
        this.stock += amount;
        
    }
    
    public void takeStock(int amount) throws Exception{
        if(amount > this.stock){
            throw new StockInsuficientException("La quantitat donada es mes gran que el stock del producte");
        } else {
            this.stock -= amount;
        }
    }

    @Override
    public int compareTo(Product o) {
        if (this.idProduct > o.idProduct) {
            return 1;
        }
        else if (this.idProduct < o.idProduct) {
            return -1;
        }
        else {
            return 0;
        }
    }

}
