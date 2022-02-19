
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;

public class Supplier extends Person implements Serializable{

    public Supplier(Integer idperson, String dni, String name, String surnames, Address address, LinkedHashSet<String> phone) {
        super(idperson, dni, name, surnames, address, phone);

    }

    public Supplier(Integer idperson, String dni, String name, String surnames, Address address) {
        super(idperson, dni, name, surnames, address);

    }

    public Supplier(Integer i, String d, String n, String c, LocalDate b, LinkedHashSet<String> p, String e, Address a) {
        super(i, d, n, c, b, p, e, a);

    }
}
