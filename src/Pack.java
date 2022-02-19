
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeSet;

public final class Pack extends Product  implements Serializable{

    private TreeSet<Product> list = new TreeSet<>();
    private int percentageDiscount = 0;

    public Pack(TreeSet<Product> list, int percentatgeDiscount, int idproduct, String name, int price) {
        super(idproduct, name, price);
        this.list = list;
        this.percentageDiscount = percentatgeDiscount;
    }

    public int getPercetatgeDescompte() {
        return percentageDiscount;
    }

    public void setPercetatgeDescompte(int percetatgeDescompte) {
        this.percentageDiscount = percetatgeDescompte;
    }

    //afegir un producte a la llista o eliminar un producte de la llista
    public void removeProduct(int p) {
        this.list.remove(p);
    }

    public void addProduct(Product i) {
        this.list.add(i);
    }

    @Override
    public String toString() {
        String products = super.toString();
        return products;

    }

    //Metode equals del id del pack
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.list);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Product) {
            Product temp = (Product) obj;
            return temp.equals(obj);
        } else {
            return false;
        }
    }

}
