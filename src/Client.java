
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;

public class Client extends Person implements Serializable{

    public Client(int idperson, String dni, String name, String surnames, Address address, LinkedHashSet<String> phone) {
        super(idperson, dni, name, surnames, address, phone);

    }

    public Client(int idperson, String dni, String name, String surnames, Address address) {
        super(idperson, dni, name, surnames, address);

    }

    public Client(int i, String d, String n, String c, LocalDate b, LinkedHashSet<String> p, String e, Address a) {
        super(i, d, n, c, b, p, e, a);

    }
}
