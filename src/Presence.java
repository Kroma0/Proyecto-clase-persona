import java.time.LocalDate;
import java.time.LocalTime;

public class Presence implements Comparable<Presence> {
    private int idWorker;
    private LocalDate date;
    private LocalTime clockInDate;
    private LocalTime clockOutDate;
    
    public Presence(int idWorker, LocalDate date, LocalTime clockInDate) {
        this.idWorker = idWorker;
        this.date = date;
        this.clockInDate = clockInDate;
    }

    

    /**
     * @return int return the idProduct
     */
    public int getidWorker() {
        return idWorker;
    }

    /**
     * @param idProduct the idProduct to set
     */
    public void setIdProduct(int idProduct) {
        this.idWorker = idProduct;
    }

    /**
     * @return LocalDate return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @return LocalDate return the clockInDate
     */
    public LocalTime getClockInDate() {
        return clockInDate;
    }

    /**
     * @param clockInDate the clockInDate to set
     */
    public void setClockInDate(LocalTime clockInDate) {
        this.clockInDate = clockInDate;
    }

    /**
     * @return LocalDate return the clockOutDate
     */
    public LocalTime getClockOutDate() {
        return clockOutDate;
    }

    /**
     * @param clockOutDate the clockOutDate to set
     */
    public void setClockOutDate(LocalTime clockOutDate) {
        this.clockOutDate = clockOutDate;
    }

    public String toString() {
        return "Presence [clockInDate=" + clockInDate + ", clockOutDate=" + clockOutDate + ", date=" + date
                + ", idWorker=" + idWorker + "]";
    }
    
    public int compareTo(Presence p) {
        if (this.idWorker > p.idWorker) {
            return 1;
        }
        else if (this.idWorker < p.idWorker) {
            return -1;
        }
        else {
            return 0;
        }
    }
}