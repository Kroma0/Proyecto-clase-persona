
import java.io.Serializable;
import java.util.HashMap;

public interface Persistable<T> extends Serializable{

    public void add(T obj);

    public void delete(int id);

    public T search(int id);

    public HashMap<Integer, T> getMap();
}
