import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;

public class Main {
    
    public static void main(String[] args)  throws IOException, Exception {
        Logger logger = Logger.getLogger(Main.class.getName());

        try {
        FileHandler fh = new FileHandler("log.txt", true);
        fh.setFormatter(new SimpleFormatter());
        logger.addHandler(fh);
        logger.setLevel(Level.ALL);
        logger.info("Start of Log");
        
        Controller g = new Controller();
        g.run();
        }catch(IOException ex){
            System.out.println (ex.toString());
            System.out.println("Could not find file");
        }
        logger.fine("End of Log");
        
    }

}



