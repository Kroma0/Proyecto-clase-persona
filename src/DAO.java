
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class DAO<T extends Identificable> implements Persistable<T>, Serializable {
    
    private HashMap<Integer, T> hmDAO = new HashMap<Integer, T>();
    
    public T get(int id){
        return this.hmDAO.get(id);
    }
    
    @Override
    public void add(T t){
        this.hmDAO.put(t.getId(), t);
    }
    
    @Override
    public HashMap<Integer, T> getMap() {
        return hmDAO;
    }

     @Override
    public void delete(int id) {
        hmDAO.remove(id);
    }

    public T search(int id) {
        if (hmDAO.containsKey(id)) {
            return hmDAO.get(id);
        } else {
            return null;
        }
    }
    
    public void modify(T obj) {
        hmDAO.replace(obj.getId(), obj);
    }
    
    public void save() throws FileNotFoundException, IOException{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("products.dat"));
        oos.writeObject(this.hmDAO);
        oos.close();
    }
    
    // public void open(String file) throws IOException{
    //     ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
    //     try {
    //         this.hmDAO = (HashMap<Integer, T>) ois.readObject();
    //     } catch (ClassNotFoundException ex) {
    //         ex.printStackTrace();
    //     }
    //     ois.close();
    // }

}
