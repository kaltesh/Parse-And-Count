package services;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains the method for parsing given URL
 */
public class ParsingService {
    private final static Logger LOGGER = Logger.getGlobal();
    private final String url;

    public ParsingService(String url) {
        this.url = url;
    }

    /**
     * @return parsed html as String
     */
    public String parseHTML() {
        StringBuilder theSiteAsSB = new StringBuilder();
        Scanner scanner = null;
        try {
            URL urlToParse = new URL(url);
            scanner = new Scanner(urlToParse.openStream());
            LOGGER.log(Level.FINE, "scanner online");
            while (scanner.hasNext()) {
                theSiteAsSB.append(scanner.next()).append(" ");
                LOGGER.log(Level.FINEST, "new word parsed by: " + Thread.currentThread().getName());
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "scanner of "  +  Thread.currentThread().getName() +  " failed");
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
                LOGGER.log(Level.FINE, "scanner closed");
            }
        }
        LOGGER.log(Level.SEVERE, "URL scanned by: " + Thread.currentThread().getName());
        return theSiteAsSB.toString();
    }
}
