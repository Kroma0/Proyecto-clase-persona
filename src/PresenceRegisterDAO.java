import java.time.LocalTime;
import java.util.TreeSet;

public class PresenceRegisterDAO {

    TreeSet<Presence> ts = new TreeSet<Presence>();

    public void add(Presence p){
        this.ts.add(p);
    }

    public TreeSet<Presence> getMap() {
        return ts;
    }

    public String getWorker(int id){
        for (Presence value : ts)
        {
            if (value.getidWorker() == id) {
                return value.toString();
            }
        }
        return null;
    }

    public void clockOut(int id){
        for (Presence value : ts)
        {
            if (value.getidWorker() == id && value.getClockOutDate() == null) {
                value.setClockOutDate(LocalTime.now());
            }
        }
    }

    
}